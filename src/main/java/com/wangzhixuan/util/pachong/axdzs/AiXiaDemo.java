package com.wangzhixuan.util.pachong.axdzs;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AiXiaDemo {
	private static String basePath = "https://www.ixdzs.com/";
	private static int waitTime = 5000;

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, SQLException {
		//获取大类型    修真，情感，武侠   这类
//		getTypes();
		
		//获取具体的书    大主宰，斗破苍穹，龙蟠     这类
//		getBooks();
		
		//获取到某一个书下面的章节，    第一张。。。。第一百章
//		getMulu();
		
		//获取到某一张下面的内容
		getContent("");
	}

	public static void getBooks() throws FailingHttpStatusCodeException, MalformedURLException, IOException, SQLException {

		String url = "https://www.ixdzs.com/sort/1/index.html";
		//https://www.ixdzs.com/sort/1/index_0_2_0_1.html
		url = url.replace("index.html", "index_0_2_0_"+1+".html");
		String html =  initWebClient(url);
		//System.out.println(html);
		Document document = Jsoup.parse(html);
		document.setBaseUri(basePath);
		Element masthead = document.select("div.box_k").select(".mt15").first();
		//System.out.println(masthead);
		
		/**
		 * 遍历获取目录
		 */
		Elements links = masthead.getElementsByTag("li");
		for (int i = 0; i < links.size(); i++) {
			Element muluChild = links.get(i);
			//System.out.println(muluChild);
			//标题
			String title = muluChild.select("h2.b_name").first().getElementsByTag("a").text().trim();
			System.out.println(title);
			
			//地址
			String getUrl = muluChild.select("h2.b_name").first().getElementsByTag("a").attr("abs:href");
			System.out.println(getUrl);
			//类型，传过来吧
			String tag = "武侠";
			System.out.println(tag);
			//作者
			String author = muluChild.select("span.l1").first().getElementsByTag("a").text().trim();
			System.out.println(author);
			//字数
			String words_num = muluChild.select("span.l2").first().text().trim();
			System.out.println(words_num);
			//状态
			String status = muluChild.select("i.cp").first().text().trim();
			System.out.println(status);
			//简介
			String bookIntro = muluChild.select("p.b_intro").first().text().trim();
			System.out.println(bookIntro);
			//最新章节
			String lastNewChapter = muluChild.select("span.l5").first().getElementsByTag("a").first().text().trim();
			System.out.println(lastNewChapter);
			String lastNewChapterGetUrl = muluChild.select("span.l5").first().getElementsByTag("a").first().attr("abs:href");
			System.out.println(lastNewChapterGetUrl);
			//最新更新时间
			String lastNewChapterTime = muluChild.select("span.l5").first().getElementsByTag("i").first().text().trim();
			System.out.println(lastNewChapterTime);
			
			
			
			String sql = "INSERT INTO `xs_book` (`title`, `title_sub`, `get_url`, `title_tag`, `author`, `author_sub`, `words_num`, `status`, `book_intro`, `last_new_chapter`, `last_new_chapter_get_url`, `last_new_chapter_time`) VALUES "
					+ "('"+title+"', '', '"+url+"', '"+tag+"', '"+author+"', '', '"+words_num+"', '"+status+"', '"+bookIntro+"', '"+lastNewChapter+"', '"+lastNewChapterGetUrl+"', '"+lastNewChapterTime+"');";
					
			//jdbcConnect.executeSql(sql);
			/*Thread.sleep(waitTime);
			//根据目录获取下面的内容
			getContent(muluChild.attr("abs:href"));
			*/
		}
		
	
	}

	private static void getTypes() throws FailingHttpStatusCodeException, MalformedURLException, IOException, SQLException {

		String url = "https://www.ixdzs.com";
		String html =  initWebClient(url);
		//System.out.println(html);
		Document document = Jsoup.parse(html);
		document.setBaseUri(basePath);
		Element masthead = document.select("div.nav_l").select(".fdl").first();
		//System.out.println(masthead);
		
		/**
		 * 遍历获取目录
		 */
		Elements links = masthead.getElementsByTag("a");
		for (int i = 0; i < links.size(); i++) {
			Element muluChild = links.get(i);
			System.out.println(muluChild);
			System.out.println(muluChild.text());
			System.out.println(muluChild.attr("abs:href"));
			String sql = "INSERT INTO `xs_type` (`type_name`, `get_url`) VALUES ('"+muluChild.text()+"', '"+muluChild.attr("abs:href")+"');";
			jdbcConnect.executeSql(sql);
			/*Thread.sleep(waitTime);
			//根据目录获取下面的内容
			getContent(muluChild.attr("abs:href"));
			*/
		}
		
	
	}

	private static void getContent(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		
		url = "http://app1.sfda.gov.cn/datasearch/face3/base.jsp?tableId=30&tableName=TABLE30&title=%E5%9B%BD%E4%BA%A7%E4%BF%9D%E5%81%A5%E9%A3%9F%E5%93%81&bcId=118103385532690845640177699192";
		String html = initWebClient(url);
		//System.out.println(html);
		Document document = Jsoup.parse(html);
		Element title = document.select("#viewimg").first();
		System.out.println("开始");
		System.out.println(title);
		System.out.println("结束");
		//Element content = document.select("#content").first();
//		System.out.println(content);
		/**
		 * 获取到里面的内容后，写入到本地文件
		 * bookname
		 */
		//ForFile.createFile(title.text(), content.html());
			
			
	}

	private static void getMulu() throws FailingHttpStatusCodeException, MalformedURLException, IOException, SQLException {
		String url = "https://www.ixdzs.com/d/171/171405/";
		url = url.replace("www", "read").replace("/d", "");
		System.out.println(url);
		//这是获取已完结的
//		String url = "https://www.ixdzs.com/sort/1/index_0_2_0_1.html";
		
		String html = initWebClient(url);
		Document document = Jsoup.parse(html);
		document.setBaseUri(url);
		Element masthead = document.select("div.catalog").first();
//		System.out.println(masthead);
		
		/**
		 * 遍历获取目录
		 */
		Elements links = masthead.getElementsByTag("li").select("li.chapter");
		for (int i = 0; i < links.size(); i++) {
			Element muluChild = links.get(i);
			String dicName = muluChild.select("li.chapter").first().getElementsByTag("a").text().trim();
			System.out.println(dicName);
			String getUrl = muluChild.select("li.chapter").first().getElementsByTag("a").attr("abs:href");
			System.out.println(getUrl);
			String sql = "INSERT INTO `xs_dic_content` (`book_id`, `dic_name`, `dic_geturl`, `is_get`, `vote_num`) VALUES "
					+ "('1', '"+dicName+"', '"+getUrl+"', '0', '0');";
			jdbcConnect.executeSql(sql);
			/*Thread.sleep(waitTime);
			//根据目录获取下面的内容
			getContent(muluChild.attr("abs:href"));
			*/
		}
		
		
	}
	
	
	public static String initWebClient(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		//构造一个webClient 模拟Chrome 浏览器
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		//屏蔽日志信息
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
		        "org.apache.commons.logging.impl.NoOpLog");
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		//支持JavaScript
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setTimeout(waitTime);
		HtmlPage rootPage = webClient.getPage(url);
		//设置一个运行JavaScript的时间
		webClient.waitForBackgroundJavaScript(waitTime);
		webClient.close();
		return rootPage.asXml();
	}
	
	

}
