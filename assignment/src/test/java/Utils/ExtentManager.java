package Utils;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
/**
 * @author Girish Kumar
 *
 * This class has Report initialization and configure extent reports
 */ 
		public class ExtentManager {

		    private static ExtentReports extent;
//		    private static final Logger logger = LoggerHelper.getLogger(ExtentManager.class);
		    private static final String macPath = System.getProperty("user.dir") + "/Reports";
		   

		    public static ExtentReports getInstance(String name) {
		        if (extent == null)
		            //Set HTML report file location
		            return createHtmlReport(name);
		        return extent;
		    }

		    private static ExtentReports createHtmlReport(String name) {
		        Calendar cal = Calendar.getInstance();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		        File reportDirectory = new File(macPath);
		        if (!reportDirectory.exists()) {
		            reportDirectory.mkdirs();
//		            logger.info("Report directory is created at " + windowsPath);
		        }
		        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(macPath+ "/Runner="+name + dateFormat.format(cal.getTime()) + ".html");
		        htmlReporter.config().setTheme(Theme.STANDARD);
		        htmlReporter.config().setTimeStampFormat("MM/dd/yyyy hh:mm:ss a");
		        htmlReporter.config().setDocumentTitle(name);
		        htmlReporter.config().setEncoding("utf-8");
		        htmlReporter.config().setReportName(name);

		        extent = new ExtentReports();
		        extent.attachReporter(htmlReporter);

		        return extent;
		    }
		    
}
		


