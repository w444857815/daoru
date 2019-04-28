<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>${titleTypeName}_第${nowPage}/页_全部作品_不限制字数_按最新排序-爱下电子书</title>
		<meta name="keywords" content="${titleTypeName},全部作品,不限制字数,最新排序，txt电子书，epub电子书，电子书免费下载" />
		<meta name="description" content="爱下电子书提供${titleTypeName}电子书下载，包含2185本作品,当前是第${nowPage}页,全部作品,不限制字数,最新排序，txt电子书，epub电子书，电子书免费下载！" />
		<link rel="alternate" media="only screen and (max-width: 640px)" href="https://m.ixdzs.com/sort/10">
		<link href="css/new1.2.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/v1.1.js"></script>
	</head>

	<body>
		<div class="h_top">
			<div class="w980 h_top_c">
				<p class="fdl">
					txt,epub电子书免费下载
				</p>
				<div class="h_top_l fdr" id="user_info">
					<div id="login" title="登录">登陆</div>
					<div id="reg">
						<a href="/reg.html" rel="nofollow" target="_blank">注册</a>
					</div>
				</div>
			</div>
		</div>
		<div class="w980 header2 mt15">
			<div class="logo fdl">
				<a href="https://www.ixdzs.com/" target="_blank"><img src="images/logo.jpg" alt="爱下电子书" /></a>
			</div>
			<div class="logo_r fdl">
				<div class="sform">
					<form name="sform" id="form" target="_blank" method="get" action="/bsearch">
						<span class="s_inp"><input class="s_inptext" type="text" name="q" id="kw" placeholder="输入小说名\作者" autocomplete="off"/><input type="submit" value="搜索" class="search"/></span>
						<div id="auto"></div>
					</form>
				</div>
				<div class="s_hot">
					<a href="/d/74/74334/" target="_blank" title="天域苍穹txt,epub下载">天域苍穹</a>
					<a href="/d/64/64960/" target="_blank" title="完美世界txt,epub下载">完美世界</a>
					<a href="/d/61/61462/" target="_blank" title="择天记txt,epub下载">择天记</a>
					<a href="/d/66/66746/" target="_blank" title="我欲封天txt,epub下载">我欲封天</a>
					<a href="/d/144/144945/" target="_blank" title="玄界之门txt,epub下载">玄界之门</a>
					<a href="/d/61/61015/" target="_blank" title="俗人回档txt,epub下载">俗人回档</a>
					<a href="/d/129/129253/" target="_blank" title="雪鹰领主txt,epub下载">雪鹰领主</a>
					<a href="/d/66/66092/" target="_blank" title="灵域txt,epub下载">灵域</a>
				</div>
			</div>
		</div>
		<div class="mt15">
			<div class="nav_g">
				<div class="nav_a">
					<a href="https://www.ixdzs.com/" title="爱下电子书" class="index">首页</a>
					
					<#list typeList as type>
						<#if type_index&lt;9>
							<a href="/sort/${type.id}/index_0_0_0_1.html" target="_blank" title="${type.typeName}">${type.typeName}</a>
						</#if>
					</#list>
				</div>
			</div>
			<div class="nav_sub">
				<div class="nav_l fdl">
					<#list typeList as type>
						<#if type_index&gt;=9>
							<a href="/sort/${type.id}/index_0_0_0_1.html" target="_blank" title="${type.typeName}">${type.typeName}</a>
						</#if>
					</#list>
					
					<!--
						<a href="/hot.html"><strong>下载排行榜</strong></a>
					-->
				</div>
			</div>
		</div>
		<div class="w980 mt15">
			<div class="crumbs">
				<ol itemscope itemtype="http://schema.org/BreadcrumbList">
					<li itemprop="itemListElement" itemscope itemtype="http://schema.org/ListItem">
						<a itemprop="item" href="/" title="返回首页"><span itemprop="name">爱下电子书</span></a>
						<meta itemprop="position" content="1" />
					</li>
					›
					<li itemprop="itemListElement" itemscope itemtype="http://schema.org/ListItem">
						<a itemprop="item" href="/sort/${titleTypeId}/index_0_${searchType}_0_1.html" title="${titleTypeName}"><span itemprop="name">${titleTypeName}</span></a>
						<meta itemprop="position" content="2" />
					</li>
				</ol>
			</div>
		</div>

		<div class="w980 mt15">
			<div class="w670 fdl">
				<div class="box_s">
					<dl>
						<dt>状态：</dt>
						<dd>
							<a href="/sort/${titleTypeId}/index_0_0_0_1.html" class="ac">全部</a>
							<a href="/sort/${titleTypeId}/index_0_1_0_1.html" rel="nofollow">连载中</a>
							<a href="/sort/${titleTypeId}/index_0_2_0_1.html">已完结</a>
						</dd>
					</dl>
				</div>
				<div class="box_k mt15">
					<ul>
						
						<#list bookList as book>
							<li>
								<div class="list_img">
									<a href="/d/${book.title}.html" title="${book.title}" target="_blank"><img src="images/5b582b985c8fce8e765ecdd7858138a1.jpg" alt="${book.title}封面" /></a>
								</div>
								<div class="list_info">
									<h2 class="b_name"><a href="/d/${book.title}.html" title="${book.title}" target="_blank">${book.title}</a></h2>
									<p class="b_info"><span class="l1">作者：<a href="/author/%E6%A2%A6%E5%85%A5%E7%A5%9E%E6%9C%BA" title="${book.author}作品集下载" target="_blank">${book.author}</a></span><span class="l2">${book.wordsNum}</span><span class="l3">状态：<i class="cp">${book.status}</i></span></p>
									<p class="b_intro">${book.bookIntro}</p>
									<p class="b_info"><span class="l5">最新章节：<a href="https://read.ixdzs.com/125/125176/p133.html" title="${book.title}最新章节:${book.lastNewChapter}" target="_blank">${book.lastNewChapter}</a><i>${book.lastNewChapterTime}</i></span></p>
									<p class="b_down"><span class="b_t"><a href="/d/${book.title}.html#txt_down" title="${book.title}" target="_blank">${book.title}</a></span><span class="b_e"><a href="/d/${book.title}.html#epub_down" title="${book.title}" target="_blank">${book.title}epub</a></span><span class="b_r"><a href="https://read.ixdzs.com/125/125176/" title="${book.title}最新章节" target="_blank">${book.title}全文阅读</a></span></p>
								</div>
							</li>
						</#list>
						
					</ul>

				</div>

				<div class="page mt15">
					<div class="pagei">
						
						<a href="/sort/${titleTypeId}/index_0_${searchType}_0_1.html" title="${titleTypeName}第一页">第一页</a> <a href="/sort/${titleTypeId}/index_0_${searchType}_0_<#if nowPage&gt;1>${nowPage-1}</#if>.html" title="${titleTypeName}上一页">&laquo;</a>
						
						<#list pageUtil as pageUnit>
							<a href="/sort/${titleTypeId}/index_0_${searchType}_0_${pageUnit}.html" class="num 
							<#if nowPage==pageUnit>
								 current
							</#if>
							" title="${titleTypeName}第${pageUnit}页">${pageUnit}</a>
						</#list>
						<a href="/sort/${titleTypeId}/index_0_${searchType}_0_<#if nowPage<maxPage>${nowPage+1}</#if>.html" title="${titleTypeName}下一页">&raquo;</a>
						<a href="/sort/${titleTypeId}/index_0_${searchType}_0_${maxPage}.html" title="${titleTypeName}最后一页">末页</a>
					</div>

				</div>
			</div>

			<div class="w320 fdr">
				<div class="box_h">
					<h3>下载排行榜</h3>
					<ul>
						<li>
							<a href="/d/12/12498/" title="大唐双龙传下载" target="_blank">大唐双龙传</a>
						</li>
						<li>
							<a href="/d/12/12734/" title="陆小凤传奇下载" target="_blank">陆小凤传奇</a>
						</li>
						<li>
							<a href="/d/1/1278/" title="覆雨翻云下载" target="_blank">覆雨翻云</a>
						</li>
						<li>
							<a href="/d/0/864/" title="射雕英雄传下载" target="_blank">射雕英雄传</a>
						</li>
						<li>
							<a href="/d/30/30235/" title="飞狐外传下载" target="_blank">飞狐外传</a>
						</li>
						<li>
							<a href="/d/50/50787/" title="穿越之主角系统下载" target="_blank">穿越之主角系统</a>
						</li>
						<li>
							<a href="/d/48/48459/" title="大炼宝下载" target="_blank">大炼宝</a>
						</li>
						<li>
							<a href="/d/0/856/" title="倚天屠龙记下载" target="_blank">倚天屠龙记</a>
						</li>
						<li>
							<a href="/d/0/858/" title="碧血剑下载" target="_blank">碧血剑</a>
						</li>
						<li>
							<a href="/d/1/1196/" title="七剑下天山下载" target="_blank">七剑下天山</a>
						</li>
						<li>
							<a href="/d/12/12880/" title="幽灵山庄下载" target="_blank">幽灵山庄</a>
						</li>
						<li>
							<a href="/d/11/11305/" title="群龙之首下载" target="_blank">群龙之首</a>
						</li>
						<li>
							<a href="/d/13/13260/" title="翠莲曲下载" target="_blank">翠莲曲</a>
						</li>
						<li>
							<a href="/d/13/13230/" title="铁剑朱痕下载" target="_blank">铁剑朱痕</a>
						</li>
						<li>
							<a href="/d/13/13305/" title="挥剑问情下载" target="_blank">挥剑问情</a>
						</li>
						<li>
							<a href="/d/12/12831/" title="万世雷池下载" target="_blank">万世雷池</a>
						</li>
						<li>
							<a href="/d/0/968/" title="七杀手下载" target="_blank">七杀手</a>
						</li>
						<li>
							<a href="/d/1/1209/" title="江湖三女侠下载" target="_blank">江湖三女侠</a>
						</li>
						<li>
							<a href="/d/11/11568/" title="毛盾天师下载" target="_blank">毛盾天师</a>
						</li>
						<li>
							<a href="/d/13/13261/" title="北山惊龙下载" target="_blank">北山惊龙</a>
						</li>
					</ul>
				</div>

				<div class="box_h mt15">
					<script type="text/javascript" src="js/s_left.js"></script>
				</div>
				<div class="box_h mt15">

				</div>
			</div>

		</div>

		<div class="w980 mt15">
			<div class="footer">
				<a target="_blank" href="#" rel="nofollow">关于我们</a>-
				<a target="_blank" href="#" rel="nofollow">法律声明</a>-
				<a target="_blank" href="#" rel="nofollow">免责声明</a>-
				<a target="_blank" href="#" rel="nofollow">意见反馈</a>-
				<a target="_blank" href="#" rel="nofollow">网站地图</a>-
				<a target="_blank" href="#" rel="nofollow">联系我们</a>
			</div>
			<div class="copy">
				Copyright 2014-2019 Ixdzs.com, Inc.All Rights Reserved.
				<a href="/sort/10/index.html" title="${titleTypeName}电子书下载">${titleTypeName}txt下载</a>,epub下载由用户上传分享，欢迎您上传电子书 </div>
		</div>
		<p id="back-to-top" style="display: block;">
			<a href="#top"><span></span>回到顶部</a>
		</p>
	</body>

</html>