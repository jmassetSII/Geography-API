package com.springjpa.auth.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IPDFRenderOption;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springjpa.auth.dto.DepartementDto;
import com.springjpa.auth.dto.VilleDto;
import com.springjpa.auth.entity.User;
import com.springjpa.auth.service.GeographyService;
import com.springjpa.auth.service.SecurityService;
import com.springjpa.auth.service.UserService;
import com.springjpa.auth.validator.UserValidator;

/**
 * @author jmasset
 *
 */
@Controller
public class UserController {
	
	@Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private GeographyService geographyService;

    @Autowired
    private UserValidator userValidator;
    
    private MailSender mailSender;
    
    private IReportEngine reportEngine;
    
    private static final String REPORT_PATH = "C:\\Users\\jmasset\\Documents\\workspace-sts-3.9.0\\springAppJPA\\src\\main\\webapp\\report\\";
    private static final String REPORT_PATH_PDF = "C:\\Users\\jmasset\\Documents\\birt_reports\\";
    
    
    /**
     * Afficher le formulaire d 'inscription
     * @param model
     * @return 
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
    	
    	model.addAttribute("userForm", new User());

        return "registration";
    }

    /**
     * Vérifier les données saisies et enregistrer les infos d'inscription 
     * @param userForm
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    /**
     * Vérifier la concordance login / mdp
     * @param model
     * @param error
     * @param logout
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Login/Mot de passe invalides");

        if (logout != null)
            model.addAttribute("message", "Vous êtes maintenant déconnecté");

        return "login";
    }
    
    /**
     * Afficher la page de reset du password
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public String resetPassword() {
    
    	return "forgotPassword";
    }
    
    /**
     * Lancer le process de reset du password et envoi par mail au user
     * @param request
     * @param email
     * @param model
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public String resetPassword(HttpServletRequest request, @RequestParam("email") String email, Model model) {
    	User user = userService.findUserByMail(email);
    	
    	if (user == null) {
    		throw new UsernameNotFoundException("User introuvable");
    	}
    	
    	String token = UUID.randomUUID().toString();
    	userService.createPasswordResetTokenForUser(user, token);
    	String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    	
    	SimpleMailMessage emailResetPassword = constructResetTokenMail(appUrl, token, request.getLocale(), user);
    	mailSender.send(emailResetPassword);
    	
//    	return messages.getMessage("message.resetPasswordEmail", null, request.getLocale());
    	
    	return "welcome";
    }

    /**
     * Afficher la page de bienvenue de l'appli
     * @param model
     * @return
     */
    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    
    /**
     * Afficher la liste des villes une fois un département sélectionné
     * @param departement
     * @return
     */
    @RequestMapping(value ="/villesList/{departement}", method = RequestMethod.GET)
    @ResponseBody
    public List<VilleDto> listVilles(@PathVariable("departement") String departement) {
    	List<VilleDto> villes = geographyService.getVilles(departement);
    	
    	return villes;
    }
    
    /**
     * Afficher les infos détaillées d'une ville via un rapport PDF
     * @param id
     * @return
     * @throws IOException
     * @throws BirtException
     */
    @RequestMapping(value="/detailVillePDF/{idVille}", method = RequestMethod.GET)
    
    public String generateDetailVille(@PathVariable("idVille") Long id) throws IOException, BirtException {
    
    	VilleDto ville = geographyService.getDetailVille(id);
    	String reportName = ville.getNom();
    	String imageUri = "http://maps.googleapis.com/maps/api/staticmap?center=" + reportName + "&zoom=14&size=600x400&&scale=2&style=feature:landscape.man_made|color:0x3D85C6";
    	
    	//Configuration du moteur BIRT et indiquer le chemin ou se trouve le template .rptDesign
    	EngineConfig config = new EngineConfig();
    	config.setBIRTHome(REPORT_PATH);
    	Platform.startup(config);
    	IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
    	reportEngine = factory.createReportEngine(config);
    	
    	//Récupérer et ouvrir le template .rptDesign
    	IReportRunnable runnable = reportEngine.openReportDesign(REPORT_PATH + "geography.rptdesign");
    	
		//Lancement de l'état du rapport
		IRunAndRenderTask runAndRenderTask = reportEngine.createRunAndRenderTask(runnable);
    	
    	//Ajustement des options pour la générations du PDF (format, alignement, nom et emplacement du rapport...)
    	IRenderOption pdfOptions = new PDFRenderOption();
    	pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.FIT_TO_PAGE_SIZE);
    	pdfOptions.setOutputFormat(IRenderOption.OUTPUT_FORMAT_PDF);
    	final String output = REPORT_PATH_PDF + reportName + "." + IRenderOption.OUTPUT_FORMAT_PDF;
    	pdfOptions.setOutputFileName(output);
    	
    	//Setter les paramètres pour permettre de récupérer des infos dynamiques à afficher dans le rapport
    	runAndRenderTask.setParameterValue("nomVille", reportName);
    	runAndRenderTask.setParameterValue("idVille", String.valueOf(id));
    	runAndRenderTask.setParameterValue("imageUri", imageUri);
    	
    	//Setter les options pdf définies pour générer le rendu
    	runAndRenderTask.setRenderOption(pdfOptions);

    	
    	
    	//Exécuter la génération du rapport
    	try {
			runAndRenderTask.run();
		} catch (EngineException e) {
			e.printStackTrace();
		}
		runAndRenderTask.close();
		
		
		return "redirect:/welcome";
    }
    
    
    
    private SimpleMailMessage constructResetTokenMail(String contextPath, String token, Locale locale, User user) {
    	String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
    	String message = "Réinitialiser le mot de passe";
    	SimpleMailMessage email = new SimpleMailMessage();
    	email.setTo(user.getEmail());
    	email.setSubject("Réinitialisation du mot de passe");
    	email.setText(message + " : " + url);
    	email.setFrom("noreply@sii.fr");
    	
    	return email;
    }
    
    
    @ModelAttribute (value = "departementList")
    public List<DepartementDto> listDepartements() {
    	
    	List<DepartementDto> listDepartements = geographyService.getDepartements();
    	
    	return listDepartements;
    }

}
