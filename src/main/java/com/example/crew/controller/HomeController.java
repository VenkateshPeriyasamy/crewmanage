package com.example.crew.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.crew.model.CrewDetails;
import com.example.crew.repo.CrewDetailsRepo;

@RequestMapping("")
@Controller
@PropertySource(value = { "classpath:application.properties" })
public class HomeController {

	@Autowired
	CrewDetailsRepo crewrepo;

	@Value("${crewmanage.filepath}")
	String filepath;

	@GetMapping("/index")
	public String home() {
		return ("index");
	}

	@RequestMapping("/adminhomepage")
	public String adminhomepage() {
		return ("adminhomepage");
	}

	

	public void writeByte(byte bytes[], String filename) throws Exception {
		File file = new File(filepath + filename);
		OutputStream os = new FileOutputStream(file);
		os.write(bytes);
		os.close();
	}

	@RequestMapping(value = { "/crewmaster" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String product(Model model, @PageableDefault(size = 3) Pageable pageable,
			@ModelAttribute("crew") @Validated CrewDetails crew, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		String records = "";
		Page<CrewDetails> page = crewrepo.findAll(pageable);
		page.get().forEach(action -> {
			try {
				writeByte(action.getImage(), action.getFilename());
			} catch (Exception e) {
				e.getMessage();
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		});
		model.addAttribute("page", page);

		// System.out.println(crews);

		return "crewmaster";
	}

	@RequestMapping(value = { "/crewmastersave" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String productSave(Model model, @PageableDefault(size = 3) Pageable pageable,

			@ModelAttribute("crew") @Validated CrewDetails crew, BindingResult result,@RequestParam(value = "profilepic") MultipartFile file,
			final RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		
		CrewDetails crewedit = new CrewDetails();

		if (crew != null) {
			
			if (file != null) {
				File tFile = multipartToFile(file);

				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
				crewedit.setFilename(timeStamp + "_" + tFile.getName());
				crewedit.setImage(readContentIntoByteArray(tFile));
			}
			 crewrepo.save(crewedit);
		}
			
			System.out.println(crew.getDob());
			crewrepo.save(crew);
		
		Page<CrewDetails> page = crewrepo.findAll(pageable);
		model.addAttribute("page", page);
		return "crewmaster";
	}


	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		String fileName = multipart.getOriginalFilename();
		String[] dStirng = fileName.split(Pattern.quote("."));
		File convFile = File.createTempFile(filepath+dStirng[0], "." + dStirng[1]);
		multipart.transferTo(convFile);
		return convFile;
	}

	private static byte[] readContentIntoByteArray(File file) {
		FileInputStream fileInputStream = null;
		byte[] bFile = new byte[(int) file.length()];
		try {
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
			for (int i = 0; i < bFile.length; i++) {
				System.out.print((char) bFile[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFile;
	}

	@RequestMapping(value = { "/crewmasterupdate" }, method = { RequestMethod.POST,
			RequestMethod.GET }, consumes = "multipart/form-data")
	public String productupdate(Model model, @PageableDefault(size = 3) Pageable pageable,

			@ModelAttribute("crew") @Validated CrewDetails crew, BindingResult result,
			@RequestParam(value = "profilepic") MultipartFile file, final RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws IllegalStateException, IOException {
		String id = request.getParameter("memberid");
		CrewDetails crewedit = crewrepo.findByMemberid(id);
		CrewDetails crewsaved = new CrewDetails();

		if (crewedit != null) {

			crewedit.setName(request.getParameter("crewname"));
			crewedit.setGender(request.getParameter("gender"));
			crewedit.setDob(request.getParameter("dateofbirth"));
			crewedit.setDesignation(request.getParameter("designation"));
			crewedit.setDoj(request.getParameter("dateofjoining"));
			crewedit.setEmail(request.getParameter("mail"));
			// crewedit.setFilename(request.getParameter("profilepic"));
			crewedit.setLocation(request.getParameter("location"));
			crewedit.setMobno(request.getParameter("mobno"));
			crewedit.setEdu(request.getParameter("edu"));
			crewedit.setExp(request.getParameter("exp"));
			if (file != null) {
				File tFile = multipartToFile(file);

				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
				crewedit.setFilename(timeStamp + "_" + tFile.getName());
				crewedit.setImage(readContentIntoByteArray(tFile));
			}
			crewsaved = crewrepo.save(crewedit);
		}

		Page<CrewDetails> page = crewrepo.findAll(pageable);
		model.addAttribute("page", page);
		return "redirect:/crewmaster";
	}

	@RequestMapping("/flightmaster")
	public String flightmaster() {
		return ("flightmaster");
	}

	@RequestMapping("/routemaster")
	public String routemaster() {
		return ("routemaster");
	}

	@RequestMapping("/tripmaster")
	public String tripmaster() {
		return ("tripmaster");
	}

}
