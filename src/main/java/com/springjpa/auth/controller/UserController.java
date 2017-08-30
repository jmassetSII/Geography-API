package com.springjpa.auth.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
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
    
    private Logger LOGGER;
    
   //private IReport reportEngine;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
    	
    	model.addAttribute("userForm", new User());

        return "registration";
    }

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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Login/Mot de passe invalides");

        if (logout != null)
            model.addAttribute("message", "Vous êtes maintenant déconnecté");

        return "login";
    }
    
    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public String resetPassword() {
    
    	return "forgotPassword";
    }
    
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

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    
    @RequestMapping(value ="/villesList/{departement}", method = RequestMethod.GET)
    @ResponseBody
    public List<VilleDto> listVilles(@PathVariable("departement") String departement) {
    	List<VilleDto> villes = geographyService.getVilles(departement);
    	
    	return villes;
    }
    
    @RequestMapping(value="/detailVillePDF/{villeId}", method = RequestMethod.GET)
    public void genereteDetailVille(@PathVariable("villeId") Long villeId) throws Exception, IOException {
    	
    	LOGGER.debug("get the details of town : " + villeId);
    	
//    	VilleDto villeDto = geographyService.getDetailVille(villeId);
//    	final String reportName = villeDto.getNom();
//    	
//    	IReportRunnable runnable = reportEngine.openReportDesign(new ClassPathResource("Birt.rptdesign").getInputStream());
//    	IRunAndRenderTask runAndRenderTask = reportEngine.createRunAndRenderTask(runnable);
//    	runAndRenderTask.setParameterValue("nomVille", reportName);
//    	
//    	IRenderOption pdfOptions = new PDFRenderOption();
//    	pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.FIT_TO_PAGE_SIZE);
//    	pdfOptions.setOutputFormat(IRenderOption.OUTPUT_FORMAT_PDF);
//    	final String output = reportName + "." + IRenderOption.OUTPUT_FORMAT_PDF;
//    	pdfOptions.setOutputFileName(output);
//    	
//    	
//    	try {
//			runAndRenderTask.run();
//		} catch (EngineException e) {
//			e.printStackTrace();
//		}
//		runAndRenderTask.close();
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
