package net.sf.jiffie;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.html.HTMLButtonElement;
import org.w3c.dom.html.HTMLTableElement;



public class MyTest {

	public static void main(String[] args) throws Exception {
		System.out.println(System.getProperty("sun.arch.data.model"));
		
//		Calendar c = GregorianCalendar.getInstance();
//		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//		System.out.println("Date " + c.getTime());
//		System.out.println(c.getFirstDayOfWeek());
//		
		
		InternetExplorer myExplorer = new InternetExplorer();
		myExplorer.setVisible(true);
		myExplorer.navigate("http://cybmis-app-3-ld/Report%20Builder/RPTN/Reportpage.aspx");
		
		Thread.sleep(1500);
		IHTMLDocument2 doc = myExplorer.getDocument(true);
		IHTMLAnchorElement aTag;
//		aTag = (IHTMLAnchorElement)doc.getElementById("MTt93");
		aTag = (IHTMLAnchorElement)doc.getElementById("TempleteTreeViewt3");
		aTag.click(true);
		
		
		Thread.sleep(1000);
		IHTMLInputElement sub = (IHTMLInputElement)doc.getElementById("ViewReportImageButton");
		System.out.println(sub);
		sub.click(true);
		doc = myExplorer.getDocument(true);
		
		ElementList tableList=null;
		IHTMLTable pageCountTable = null;
		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a");
		try {
			tableList = doc.getElementListByTag("TABLE");
		} catch (JiffieException e) {
			System.out.println("In TimeParser.DayTimeParser() - No table tag found");
			e.printStackTrace();
		}
		
		String pattern = ".*?id=\"ReportViewer1[^\"]*[^>]*>([\\d]+).*";
		System.out.println(pattern);
		Pattern p = Pattern.compile(pattern);
		
	    
		for (int i=0; i<tableList.size();i++)
		{
			if(((IHTMLTable)tableList.get(i)).getInnerHtml().contains("getElementById\\('ReportViewer1'\\)\\.ClientController\\.ActionHandler"))
			{
				System.out.println(((IHTMLTable)tableList.get(i)).getInnerHtml());
//				pageCountTable = (IHTMLTable)tableList.get(i);
				Matcher m = p.matcher(((IHTMLTable)tableList.get(i)).getInnerHtml());
				if(m.find())
				{
					System.out.println("Pages = " + m.group(0));
				}
			}
		}
		
		long totalSeconds = TimeParser.DayTimeParser(doc);
//		
//
//		ElementList tableList = doc.getElementListByTag("TABLE");
//		IHTMLTable swipeTable = null;
//		for (int i=0; i<tableList.size();i++)
//		{
//			if(((IHTMLTable)tableList.get(i)).getInnerHtml().contains("10557"))
//			{
//				System.out.println(((IHTMLTable)tableList.get(i)).getInnerHtml());
//				swipeTable = (IHTMLTable)tableList.get(i);
//			}
//		}
//		
//		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a");
//		for (int i=0; i<swipeTable.getRows().size();i++)
//		{
//			if(swipeTable.getRow(i).getInnerHtml().contains("10557"))
//			{
//				Date date1 = format.parse (swipeTable.getRow(i).getCell(1).getInnerText() + " " + swipeTable.getRow(i).getCell(4).getInnerText());
//				System.out.println(date1);
//			}
//		}
//		ElementList elementList = doc.getElementListByTag("TABLE");
//		for (int i=0;i<elementList.size();i++)
//			{
//				if (!(elementList.get(i) instanceof  IHTMLCommentElement))
//					System.out.println(((IHTMLElement)elementList.get(i)).getID());
//			}
//		
//		
//		myExplorer.navigate("http://cybagemis.cybage.com/Framework/Iframe.aspx");
//		Thread.sleep(1000);
//		IHTMLDocument2 doc = myExplorer.getDocument(true);
//		 
//		
//		System.out.println(doc.getElementById("MTt93"));
//		IHTMLAnchorElement aTag = (IHTMLAnchorElement)doc.getElementById("MTt93");
//		aTag.click(true);
//		
//		Thread.sleep(1500);
//		
//		doc = myExplorer.getDocument(true);
//		System.out.println(doc.getUrl());
//		
//		aTag = (IHTMLAnchorElement)doc.getElementById("TempleteTreeViewt1");
//		System.out.println(aTag);
//		
//		ElementList elementList = doc.getElementList();
//		for (int i=0;i<elementList.size();i++)
//		{
//			if (!(elementList.get(i) instanceof  IHTMLCommentElement))
//				System.out.println(((IHTMLElement)elementList.get(i)).getID());
//		}
		

		
//		aTag = (IHTMLAnchorElement)doc.getElementById("TempleteTreeViewt1");
//		aTag = (IHTMLAnchorElement)doc.getElementById("TempleteTreeViewt3");
//		aTag.click(true);
		
	}

}
