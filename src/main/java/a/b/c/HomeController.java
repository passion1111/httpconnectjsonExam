package a.b.c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	@RequestMapping(value = "/apitest", method = RequestMethod.GET)
	public String home2(Locale locale, Model model) throws IOException {
		
		
		
		//API 요청 
		//응답 
		URL url=new URL("http://localhost:8080/apitest3");
		HttpURLConnection con=(HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Charset", "UTF-8"); 
		// 
//		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		StringBuilder sb = new StringBuilder();
		try{
			//Stream을 처리해줘야 하는 귀찮음이 있음. 
			BufferedReader br = new BufferedReader(
					new InputStreamReader(con.getInputStream(), "utf-8"));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			br.close();
			System.out.println("" + sb.toString());
			ObjectMapper mapper = new ObjectMapper(); 
			String json = "{\"name\":\"mkyong\", \"age\":\"37\"}";
			Map<String, String> map = mapper.readValue(json, Map.class);
			HashMap<String,Object> hashvalue=mapper.readValue(sb.toString(), HashMap.class);
			System.out.println("" + sb.toString());
			System.out.println("" + hashvalue);
			System.out.println("" + hashvalue.get("test"));

	} catch (Exception e) {
		System.err.println(e.toString());
	}
		return "home";
	}
	
	
	
	
	@RequestMapping(value = "/apitest3", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> home3(Locale locale, Model model) throws IOException {
		
		HashMap check=new HashMap();
		check.put("test", "test2");
		
		
		return check;
	}
	
	
	  @RequestMapping(value="/aputest5", method = RequestMethod.GET)
	    @ResponseBody
	    public Map<String,Object> NoticehitNum(@RequestParam int num) {
	        Map<String, Object> map=new HashMap();
	        map.put("hitnum", 1);
	        return map;  // {"hitnum", 99}
	    }


	
	
	
	@RequestMapping(value = "/apitest4", method = RequestMethod.GET)
	@ResponseBody
	public String home4(Locale locale, Model model) throws IOException {
		
		HashMap check=new HashMap();
		check.put("test", "test2");
		
		
		return "test333";
	}
	@RequestMapping(value = "/apitest5", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> home5(Locale locale, Model model) throws IOException {
		
		HashMap check=new HashMap();
		check.put("test", "test2");
		
		
		return check;
	}
}
