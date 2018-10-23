package com.wangzhixuan.util.bigfile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
 
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
 

//https://blog.csdn.net/sundy_fly/article/details/78134322?utm_source=blogxgwz0
public class ParseXlsxExcel extends DefaultHandler {
	private OPCPackage opcPackage = null;
	private XSSFReader xssfReader = null;
	private boolean nextIsStr;
	private String cellContent; //单元格的内容
	private ArrayList<String> sheetList; // sheet的所有内容
	private ArrayList<String> excelList; // Excel的所有内容
	private SharedStringsTable sst;
	private int currentColumn = 0;
	private XMLReader reader;
	
	/**
	 * @param path .xlxs后缀的Excel文件
	 * @param sheetId 
	 * @throws IOException
	 * @throws OpenXML4JException
	 * @throws SAXException
	 */
	public ParseXlsxExcel(String path,int sheetId) throws IOException, OpenXML4JException, SAXException{
		init(new File(path));
		parseSheet(sheetId);
	}
	
	public ParseXlsxExcel(File file,int sheetId) throws IOException, OpenXML4JException, SAXException{
		init(file);
		parseSheet(sheetId);
	}
	
	/**
	 * @param file	.xlxs 后缀的Excel文件
	 * @param flag	true 表示查询所有 ，false 表示只获取sheetId为1的内容
	 * @throws IOException
	 * @throws OpenXML4JException
	 * @throws SAXException
	 */
	public ParseXlsxExcel(File file,boolean flag) throws IOException, OpenXML4JException, SAXException{
		initAll(file, flag);
	}
	
	public ParseXlsxExcel(String path,boolean flag) throws IOException, OpenXML4JException, SAXException{
		initAll(new File(path), flag);
	}
 
	private void initAll(File file, boolean flag)throws InvalidFormatException, IOException, OpenXML4JException,SAXException {
		init(file);
		if(flag){
			parseAllSheet();
		}else {
			parseSheet(1);
		}
	}
	
	private void parseAllSheet() {
		int x = 0;
		int y = 0;
		while(x==y){
			try {
				parseSheet(++x);
				excelList.addAll(sheetList);
				y=x;
				currentColumn=0;
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}
 
	private void init(File file) throws IOException, OpenXML4JException, SAXException{
		opcPackage = OPCPackage.open((file), PackageAccess.READ);
		sheetList = new ArrayList<String>();
		excelList = new ArrayList<String>();
		xssfReader = new XSSFReader(opcPackage);
		sst = xssfReader.getSharedStringsTable();
		reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
		reader.setContentHandler(this);
	}
	
	public void close() throws IOException{
		if(opcPackage!=null) opcPackage.close();
	}
	
	private void parseSheet(int sheetId) throws IOException, OpenXML4JException, SAXException{
		sheetList.clear();
		InputStream inStream = xssfReader.getSheet("rId"+sheetId);
		InputSource inputSource = new InputSource(inStream);
		reader.parse(inputSource);
	}
 
	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		if(qName.equals("c")){ // c为单元格，v为值，row为行。
			String type = attributes.getValue("t");
			if(type!=null && type.equals("s")){
				nextIsStr = true;
			}else {
				nextIsStr = false;
			} 
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		cellContent = new String(ch, start, length);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(nextIsStr&&cellContent!=""&&cellContent!=null){
			try {
				int idx = Integer.parseInt(cellContent);
				cellContent = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
			} catch (Exception e) {
//				 e.printStackTrace();
			}
		}
		if(qName.equals("v")){
			sheetList.add(currentColumn++, cellContent);
			cellContent="";
		}
		
	}
	
	/** sheet的所有内容 */
	public ArrayList<String> getSheetList() {
		return sheetList;
	}
	
	/** Excel的所有内容 */
	public ArrayList<String> getExcelList() {
		return excelList;
	}
	
	public static void main(String[] args) throws Exception{
		long start = System.currentTimeMillis();
		ParseXlsxExcel excel = new ParseXlsxExcel("D:/zhuanmen.xlsx",true);
		excel.close();
		ArrayList<String> list = excel.getExcelList();
		long end = System.currentTimeMillis();
		
		System.out.println(list.size());//19677984
		System.out.println(end-start); //16389ms
	}
}
