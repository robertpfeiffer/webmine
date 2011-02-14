(ns webmine.parser-test
 (:use clojure.test
       webmine.parser))

(def simple-html
"
<html>
<body>

<p>foo</p>
<p>bar</p>
<p>baz</p>

</body>
</html>
")

(deftest simple-walk
  (is (= "\n\nfoo\nbar\nbaz\n\n"
    (text-from-dom (dom simple-html)))))

(def tc
{:title "In The Fight Against IT Waste, 1E Releases NightWatchman 6.0", :link "http://feedproxy.google.com/~r/Techcrunch/~3/tEFtaTZo8jI/", :content "<p><img src=\"http://tctechcrunch.files.wordpress.com/2010/11/dashboard.jpg\" />Computer power management software company, <a href=\"http://www.1e.com/index.aspx\">1E</a> has released a new version of its marquee product, <a href=\"http://www.1e.com/softwareproducts/nightwatchman/index.aspx\">NightWatchman</a>.</p> <p>Like its predecessors, version 6.0, helps corporations manage their network of computers to optimize energy efficiency. It gives IT managers the ability to remotely power down computers and establish energy-saving settings (ie. automatic shutdown of desktops during the weekend).</p> <p>In the latest version, <a href=\"http://www.crunchbase.com/company/1e\">1E</a> has added three key features: a new web-based dashboard (to help managers oversee the entire company&#8217;s computer power usage), improved diagnostic tools to determine why a computer hasn&#8217;t properly powered down, and tariff calculations based on location.</p> <p>Given how the price of energy can fluctuate significantly on a region by region basis, the new location-based calculations will help companies more accurately assess how much they&#8217;re saving on energy usage. Energy efficiency as it relates to IT management is becoming an increasingly important field, and according to Gartner Research, in two years more than half of mid and large-sized businesses will centrally manage their desktops&#8217; energy consumption.</p> <p>Although the average NightWatchman PC only saves $36 a year in energy costs, those incremental savings yield significant sums in aggregate. Several of 1E&#8217;s clients are large corporations with massive IT operations, such as AT&amp;T, Ford and Dell. According to 1E, NightWatchman has 4.6 million licensed users around the world, a group that has collectively saved $530 million in energy costs.</p> <div class=\"cbw snap_nopreview\"><div class=\"cbw_header\"><script src=\"http://www.crunchbase.com/javascripts/widget.js\" type=\"text/javascript\"></script><div class=\"cbw_header_text\"><a href=\"http://www.crunchbase.com/\">CrunchBase Information</a></div></div><div class=\"cbw_content\"><div class=\"cbw_subheader\"><a href=\"http://www.crunchbase.com/company/1e\">1E</a></div><div class=\"cbw_subcontent\"><script src=\"http://www.crunchbase.com/cbw/company/1e.js\" type=\"text/javascript\"></script></div><div class=\"cbw_footer\">Information provided by <a href=\"http://www.crunchbase.com/\">CrunchBase</a></div></div></div> <br /> <a rel=\"nofollow\" href=\"http://feeds.wordpress.com/1.0/gocomments/tctechcrunch.wordpress.com/238571/\"><img alt=\"\" border=\"0\" src=\"http://feeds.wordpress.com/1.0/comments/tctechcrunch.wordpress.com/238571/\" /></a> <a rel=\"nofollow\" href=\"http://feeds.wordpress.com/1.0/godelicious/tctechcrunch.wordpress.com/238571/\"><img alt=\"\" border=\"0\" src=\"http://feeds.wordpress.com/1.0/delicious/tctechcrunch.wordpress.com/238571/\" /></a> <a rel=\"nofollow\" href=\"http://feeds.wordpress.com/1.0/gofacebook/tctechcrunch.wordpress.com/238571/\"><img alt=\"\" border=\"0\" src=\"http://feeds.wordpress.com/1.0/facebook/tctechcrunch.wordpress.com/238571/\" /></a> <a rel=\"nofollow\" href=\"http://feeds.wordpress.com/1.0/gotwitter/tctechcrunch.wordpress.com/238571/\"><img alt=\"\" border=\"0\" src=\"http://feeds.wordpress.com/1.0/twitter/tctechcrunch.wordpress.com/238571/\" /></a> <a rel=\"nofollow\" href=\"http://feeds.wordpress.com/1.0/gostumble/tctechcrunch.wordpress.com/238571/\"><img alt=\"\" border=\"0\" src=\"http://feeds.wordpress.com/1.0/stumble/tctechcrunch.wordpress.com/238571/\" /></a> <a rel=\"nofollow\" href=\"http://feeds.wordpress.com/1.0/godigg/tctechcrunch.wordpress.com/238571/\"><img alt=\"\" border=\"0\" src=\"http://feeds.wordpress.com/1.0/digg/tctechcrunch.wordpress.com/238571/\" /></a> <a rel=\"nofollow\" href=\"http://feeds.wordpress.com/1.0/goreddit/tctechcrunch.wordpress.com/238571/\"><img alt=\"\" border=\"0\" src=\"http://feeds.wordpress.com/1.0/reddit/tctechcrunch.wordpress.com/238571/\" /></a> <img alt=\"\" border=\"0\" src=\"http://stats.wordpress.com/b.gif?host=techcrunch.com&amp;blog=11718616&amp;post=238571&amp;subd=tctechcrunch&amp;ref=&amp;feed=1\" width=\"1\" height=\"1\" /> <p><a href=\"http://feedads.g.doubleclick.net/~at/w3bq_Lf15MuCCqeCkVfh9eMYs7g/0/da\"><img src=\"http://feedads.g.doubleclick.net/~at/w3bq_Lf15MuCCqeCkVfh9eMYs7g/0/di\" border=\"0\" ismap=\"true\"></img></a><br/> <a href=\"http://feedads.g.doubleclick.net/~at/w3bq_Lf15MuCCqeCkVfh9eMYs7g/1/da\"><img src=\"http://feedads.g.doubleclick.net/~at/w3bq_Lf15MuCCqeCkVfh9eMYs7g/1/di\" border=\"0\" ismap=\"true\"></img></a></p><div class=\"feedflare\"> <a href=\"http://feeds.feedburner.com/~ff/Techcrunch?a=tEFtaTZo8jI:MRP7yJsevfA:2mJPEYqXBVI\"><img src=\"http://feeds.feedburner.com/~ff/Techcrunch?d=2mJPEYqXBVI\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/Techcrunch?a=tEFtaTZo8jI:MRP7yJsevfA:7Q72WNTAKBA\"><img src=\"http://feeds.feedburner.com/~ff/Techcrunch?d=7Q72WNTAKBA\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/Techcrunch?a=tEFtaTZo8jI:MRP7yJsevfA:yIl2AUoC8zA\"><img src=\"http://feeds.feedburner.com/~ff/Techcrunch?d=yIl2AUoC8zA\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/Techcrunch?a=tEFtaTZo8jI:MRP7yJsevfA:-BTjWOF_DHI\"><img src=\"http://feeds.feedburner.com/~ff/Techcrunch?i=tEFtaTZo8jI:MRP7yJsevfA:-BTjWOF_DHI\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/Techcrunch?a=tEFtaTZo8jI:MRP7yJsevfA:D7DqB2pKExk\"><img src=\"http://feeds.feedburner.com/~ff/Techcrunch?i=tEFtaTZo8jI:MRP7yJsevfA:D7DqB2pKExk\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/Techcrunch?a=tEFtaTZo8jI:MRP7yJsevfA:qj6IDK7rITs\"><img src=\"http://feeds.feedburner.com/~ff/Techcrunch?d=qj6IDK7rITs\" border=\"0\"></img></a> </div><img src=\"http://feeds.feedburner.com/~r/Techcrunch/~4/tEFtaTZo8jI\" height=\"1\" width=\"1\"/>", :des "<img src=\"http://tctechcrunch.files.wordpress.com/2010/11/watchman.jpg\" />Computer power management software company, <a href=\"http://www.1e.com/index.aspx\">1E</a> has released a new version of its marquee product, <a href=\"http://www.1e.com/softwareproducts/nightwatchman/index.aspx\">NightWatchman</a>. Like its predecessors, version 6.0, helps corporations manage their network of computers to optimize energy efficiency. It gives IT managers the ability to remotely power down computers and establish energy-saving settings (ie. automatic shutdown of desktops during the weekend). In the latest version, <a href=\"http://www.crunchbase.com/company/1e\">1E</a> has added three key features.<img alt=\"\" border=\"0\" src=\"http://stats.wordpress.com/b.gif?host=techcrunch.com&amp;blog=11718616&amp;post=238571&amp;subd=tctechcrunch&amp;ref=&amp;feed=1\" width=\"1\" height=\"1\" />", :date "2010-11-01T16:54:57.582Z"})

(def clean-tc 
{:title "In The Fight Against IT Waste, 1E Releases NightWatchman 6.0", :link "http://feedproxy.google.com/~r/Techcrunch/~3/tEFtaTZo8jI/", :content "Computer power management software company, 1E has released a new version of its marquee product, NightWatchman. Like its predecessors, version 6.0, helps corporations manage their network of computers to optimize energy efficiency. It gives IT managers the ability to remotely power down computers and establish energy-saving settings (ie. automatic shutdown of desktops during the weekend). In the latest version, 1E has added three key features: a new web-based dashboard (to help managers oversee the entire company’s computer power usage), improved diagnostic tools to determine why a computer hasn’t properly powered down, and tariff calculations based on location. Given how the price of energy can fluctuate significantly on a region by region basis, the new location-based calculations will help companies more accurately assess how much they’re saving on energy usage. Energy efficiency as it relates to IT management is becoming an increasingly important field, and according to Gartner Research, in two years more than half of mid and large-sized businesses will centrally manage their desktops’ energy consumption. Although the average NightWatchman PC only saves $36 a year in energy costs, those incremental savings yield significant sums in aggregate. Several of 1E’s clients are large corporations with massive IT operations, such as AT&T, Ford and Dell. According to 1E, NightWatchman has 4.6 million licensed users around the world, a group that has collectively saved $530 million in energy costs. CrunchBase Information1EInformation provided by CrunchBase", :des "Computer power management software company, 1E has released a new version of its marquee product, NightWatchman. Like its predecessors, version 6.0, helps corporations manage their network of computers to optimize energy efficiency. It gives IT managers the ability to remotely power down computers and establish energy-saving settings (ie. automatic shutdown of desktops during the weekend). In the latest version, 1E has added three key features.", :date "2010-11-01T16:54:57.582Z"})

(deftest scrub-html-test
  (is (= 
       clean-tc
       (scrub-html tc :des :content))))

(deftest strip-space-test
  (is (= "foo  bar"
         (strip-space "\n\n\tfoo  bar\n \t   \n\n")))
  (is (= "foo\nbar"
         (strip-space "\n\n\tfoo  \n\n\n\n  bar\n \t   \n\n")))
  (is (= "foo\nbar"
         (strip-space "\n\n\tfoo \n\t  \nbar\n \t   \n\n"))))

(deftest charset-test
  (is (= "windows-1250"
         (charset (dom "<!DOCTYPE html PUBLIC \"-//Lidovky//DTD HTML 4//EN\" \"http://g.lidovky.cz/dtd/n3_uni.dtd\">
<html><head>
<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1250\">
<meta http-equiv=\"cache-control\" content=\"no-cache\">
<meta name=\"robots\" content=\"all\">
<title>Zemřel Pavel Vondruška, muzikant a jeden z 'Cimrmanů' - www.lidovky.cz</title>
</head><body></body></html>")))))

(def html-with-garbage
"
<html>
<body>

<p>foo</p>
<p>bar</p>
<p>baz</p>

<img src=\"http://feeds.feedburner.com/~ff/Techcrunch?d=qj6IDK7rITs\" border=\"0\"></img>

<form name=\"input\" action=\"html_form_action.asp\" method=\"get\">
Username: <input type=\"text\" name=\"user\" />
<input type=\"submit\" value=\"Submit\" />
</form>

<script type=\"text/javascript\">
document.write(\"Hello World!\")
</script>

<iframe src=\"html_intro.asp\" width=\"100%\" height=\"300\">
  <p>Your browser does not support iframes.</p>
</iframe>

</body>
</html>
")

(def html-without-garbage
"<?xml version=\"1.0\" encoding=\"UTF-16\"?><html xmlns:html=\"http://www.w3.org/1999/xhtml\"><body>\n\n<p>foo</p>\n<p>bar</p>\n<p>baz</p>\n\n\n\n\n\n\n\n\n\n</body></html>")

(deftest clean-html-test
  (is (= html-without-garbage
	 (html-str (strip-blacklist
		    (dom html-with-garbage))))))

(def html-with-div-span
"
<html>
<body>
<span>fizzle<p>foo</p></span>
<div>bar<p>baz</p></div>
</body>
</html>
")

(def html-from-div-span
"<?xml version=\"1.0\" encoding=\"UTF-16\"?><html xmlns:html=\"http://www.w3.org/1999/xhtml\"><body>\nfizzle<p>foo</p>\nbar<p>baz</p>\n</body></html>")

(deftest clean-html-test
  (is (= html-from-div-span
	 (html-str (raise-content (dom html-with-div-span))))))

(def dirty-html-with-div-span
"
<html>
<body><script type=\"text/javascript\">
document.write(\"Hello World!\")
</script><iframe src=\"html_intro.asp\" width=\"100%\" height=\"300\">
  <p>Your browser does not support iframes.</p></iframe>
<span>fizzle<p>foo</p></span>
<div>bar<p>baz</p></div>
</body>
</html>
")

(deftest pretty-dom-test
  (is (= html-from-div-span
	 (html-str (pretty-dom (dom dirty-html-with-div-span))))))
>>>>>>> c813d949a15157482fa72fb6c985c79c4556ee85
