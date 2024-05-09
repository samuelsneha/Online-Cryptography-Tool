package com.cgtmse.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cgtmse.entity.FileUpload;
import com.cgtmse.entity.Home;
import com.cgtmse.entity.Login;
import com.cgtmse.entity.Registration;
import com.cgtmse.entity.User;
import com.cgtmse.entity.YourActivity;
import com.cgtmse.service.UserService;
import com.cgtmse.service.cryptoService;
import com.cgtmse.utils.FileUtils;
import com.cgtmse.utils.HashUtils;

@Controller
public class frontendController {
	
	String email;
	
	@Autowired
	UserService userService;
    
	@Autowired
	UserDetailsService userDetailsService;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@Autowired
	cryptoService cryptoservice;

	//@ModelAttribute("user")
	//public 
	
	@GetMapping("/test")
	public String testUser( Model model) {
		model.addAttribute("user", new User() );
		//model.addAttribute("success","SDFG");
		return "registration";
	}
	
	@GetMapping("/register")
	public String registerUserGet( Model model ) {
		System.out.println("reached register get");
		model.addAttribute("registration", new Registration()); //doing "user", new User() wont redirect you to /register
		return "registration";
	}
	
	@PostMapping("/register")
	public String registerUser( @ModelAttribute Registration registration, @ModelAttribute Login login , Model model, RedirectAttributes redirAttrs  ) throws NoSuchAlgorithmException { //RedirectAttributes redirAttrs
		System.out.println( " reached register post ");
		model.addAttribute("registration", registration );//try commenting this and see acc to chatGPT's recommendations
		User user = convertRegistrationToUser(registration);
		boolean result = userService.findUser(user.getEmail());
		System.out.println( result +" ans is ");
		if( result ) {
			System.out.println("user exists");
			model.addAttribute("alreadyexists", "User already exists. Kindly login");
			//redirAttrs.addFlashAttribute("alreadyexists", "User already exists. Kiindly login");
			return "registration";
		}else {
			System.out.println("user does not exists");
			userService.saveUser(user);
			System.out.println( " finished service");
			model.addAttribute("newusercreated", "New User Successfully created. You can login now.");
		    //redirAttrs.addFlashAttribute("newusercreated", "New User Successfully created. You can login now.");
			System.out.println( " finished attributes");
			return "login";
		}
	}
	
	private User convertRegistrationToUser(Registration registration) throws NoSuchAlgorithmException {
	    User user = new User();
	    // Set properties from Registration to User
	    user.setFirstName(registration.getFirstName());
	    user.setLastName(registration.getLastName());
	    user.setEmail(registration.getEmail());
	    user.setPassword(HashUtils.toHexString( registration.getPassword() ));
	    System.out.println(HashUtils.toHexString( registration.getPassword() ));
	    return user;
	}
	
	@GetMapping("/login")
	public String loginForm( Model model ) {
		System.out.println("reached login get");
		model.addAttribute("login", new Login());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginSubmit( @ModelAttribute Login login, @ModelAttribute Home home, @ModelAttribute Registration registration , Model model,RedirectAttributes redirAttrs ) { 
		try {
			System.out.println("reached login post");
			model.addAttribute("login", login );
//			redirAttrs.addFlashAttribute("error", "Invalid username or password");
//			redirAttrs.addFlashAttribute("logout", "Successfully logged  out");
			email = login.getEmail();
			System.out.println( email );
			UserDetails userDetails  = userDetailsService.loadUserByUsername(email);
			//if the user does not exists in db
			if( userDetails == null ) {
				System.out.println( "User does not exists in db");
				//redirAttrs.addFlashAttribute("notFound", "User not found in the database. Kindly Register");
				//model.addAttribute("notfound", "User not found in the database. Kindly Register");
				return "registration";
			}
			else {
				System.out.println( "******" +login.getPassword()+ userDetails.getPassword() );
				System.out.println(HashUtils.toHexString( login.getPassword()));
				if (( HashUtils.toHexString( login.getPassword()).equals(userDetails.getPassword()) )) {
					System.out.println("password matched");
					model.addAttribute("email", email);
					redirAttrs.addFlashAttribute("passwordsmatched", "Credentials matched! Welcome to Home Page");
					//session.setAttribute("email", email);
					return "redirect:/home?email="+email;
				}
				else {
					System.out.println("passwords didnt match");
					//redirAttrs.addFlashAttribute("passwordsdidntmatched", "Credentials didnt match. Kindly try again!");
					model.addAttribute( "passwordsdidntmatched", "Credentials didnt match. Kindly try again!");
					return "login";
				}	
			}
			
		}catch( UsernameNotFoundException  u ) {
			System.out.println( u );
			redirAttrs.addFlashAttribute("usernotfound", "User not found in database. Kindly Register");
			return "redirect:/register";
		}
		catch( Exception e ) {
			System.out.println( e );
			return null;
		}
	}
	
	@GetMapping("/home")
	public String returnToHome( Model model, @RequestParam String email ) {
		System.out.println("reached home get");
		System.out.println(" reached home get controller and email is "+email);
		model.addAttribute("home", new Home());
		model.addAttribute("email", email);
		return "home";
	}
	
	@PostMapping("/home")
	public String returnToHome(@RequestParam String email , @ModelAttribute Home home,  Model model,RedirectAttributes redirAttrs ) {
		System.out.println(" reached home post controller and email is "+email);
		//String email = (String) session.getAttribute("email");
		model.addAttribute("email", email);
		return "home";
	}
	
	
	
//	@GetMapping("/yourActivity")
//	public String yourActivityGet( Model model ) {
//		System.out.println("reached yourActivity get");
//		model.getAttribute("email");
//	    model.addAttribute( "yourActivity" , new YourActivity());
//		return "yourActivity";
//	}
	
	@GetMapping("/yourActivity")
	public String yourActivityPost( Model model, @RequestParam(required = false) String email ) {
		try {
			System.out.println( "reached yourActivity get " );
			System.out.println("Email: " + email);
		    model.addAttribute("email", email);
		    model.addAttribute("yourActivity", new YourActivity());
		    return "yourActivity";
		} catch( Exception e ) {
			System.out.println(e);
	        return "sample";
		}
		//model.addAttribute( "yourActivity", youractivity);
		//model.addAttribute("email", email);
		//return "redirect:/yourActivity";
		//return new ModelAndView("yourActivity");
		//return "yourActivity";
	}
	
//	@GetMapping("/redirectToActivity")
//	public String redirectToActivity( Model model ) {
//		System.out.println("reached yourActivity get");
//	    model.addAttribute( "yourActivity" , new YourActivity());
//	    return "redirect:/yourActivity";
//	}
//		
//	@PostMapping("/redirectToActivity")
//	public String redirectToActivity( @ModelAttribute YourActivity youractivity, Model model ) {
////		System.out.println( "reached yourActivity post " );
////		model.addAttribute( "yourActivity", youractivity);
//		model.addAttribute("email", email);
//		return "redirect:/yourActivity";
//	}
	
	
	//@ModelAttribute("user") User user
	//userService.saveUser(user);
	//RedirectAttributes redirAttrs
	//redirAttrs.addFlashAttribute("success", "Everything went nice");
	
	@PostMapping("/uploadFile")
	public ResponseEntity<?>  uploadFile( @RequestParam("file") MultipartFile file,  @RequestParam("email") String email, Model model, RedirectAttributes redirAttrs ) throws FileNotFoundException {
		System.out.println("ok done");
		try {
//			if( file.isEmpty() ) {
//				System.out.println("no file uploaded"); 
//				model.addAttribute("email", email);
//				model.addAttribute("fileUpload", new FileUpload());
//			}
//			else {
				byte[] bytes = file.getBytes();
				//String fileType = file.getContentType();
				String mimeType = FileUtils.getRealMimeType(file);
				System.out.println("minetype is "+mimeType);
				//Files.probeContentType(file.g)
				//String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				//System.out.println(" bytes is "+bytes);
				//System.out.println("file type is "+fileType); 
				System.out.println("email in uploadfile is "+email);
//				String result = cryptoservice.encryptAESFile( bytes, fileType );
//				model.addAttribute("email", email);
//				model.addAttribute("fileUpload", new FileUpload());
				//return cryptoservice.encryptAESFile( bytes, fileType );
				return cryptoservice.encryptAESFile( bytes, mimeType, redirAttrs );
//			}
		} catch (IOException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
			e.printStackTrace();
			File file1 = new File("new.txt");
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file1));
			System.out.println( e );
			return ResponseEntity.ok()
			            .contentType(MediaType.APPLICATION_OCTET_STREAM)
			            .body(resource);
		}
		//return "fileUpload";
		
	}
	
	@PostMapping("/decryptUploadFile")
	public ResponseEntity<?> decryptUploadFile( @RequestParam("file") MultipartFile file,@RequestParam("email") String email, Model model, RedirectAttributes redirAttrs ) throws FileNotFoundException {
		System.out.println("ok done");
		try {
//			if( file.isEmpty() ) {
//				System.out.println("no file uploaded"); 
//				model.addAttribute("email", email);
//				model.addAttribute("fileUpload", new FileUpload());
//			}
//			else {
				byte[] bytes = file.getBytes();
				//String fileType = file.getContentType();
				String mimeType = FileUtils.getRealMimeType(file);
				System.out.println("minetype is "+mimeType);
				//Files.probeContentType(file.g)
				//String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				//System.out.println(" bytes is "+bytes);
				//System.out.println("file type is "+fileType); 
				System.out.println("email in uploadfile is "+email);
//				String result = cryptoservice.encryptAESFile( bytes, fileType );
//				model.addAttribute("email", email);
//				model.addAttribute("fileUpload", new FileUpload());
				//return cryptoservice.encryptAESFile( bytes, fileType );
				return cryptoservice.decryptAESFile( bytes, mimeType, redirAttrs );
//			}
		} catch (IOException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
			e.printStackTrace();
			String s = "";
			File file1 = new File("new.txt");
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file1));
			System.out.println( e );
			return ResponseEntity.ok()
			            .contentType(MediaType.APPLICATION_OCTET_STREAM)
			            .body(s.getBytes());
		}
		//return "fileUpload";
		
	}
	
}
