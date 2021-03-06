// https://www.pirilampo.org/styles/readtheorg/js/readtheorg.js
$(function() {
    $('.note').before("<p class='admonition-title note'>Note</p>");
    $('.seealso').before("<p class='admonition-title seealso'>See also</p>");
    $('.warning').before("<p class='admonition-title warning'>Warning</p>");
    $('.caution').before("<p class='admonition-title caution'>Caution</p>");
    $('.attention').before("<p class='admonition-title attention'>Attention</p>");
    $('.tip').before("<p class='admonition-title tip'>Tip</p>");
    $('.important').before("<p class='admonition-title important'>Important</p>");
    $('.hint').before("<p class='admonition-title hint'>Hint</p>");
    $('.error').before("<p class='admonition-title error'>Error</p>");
    $('.danger').before("<p class='admonition-title danger'>Danger</p>");
});
$( document ).ready(function() {
    $(document).on('click', "[data-toggle='wy-nav-top']", function() {
		$("[data-toggle='wy-nav-shift']").toggleClass("shift");
		$("[data-toggle='rst-versions']").toggleClass("shift");
    });
    $(document).on('click', ".wy-menu-vertical .current ul li a", function() {
		$("[data-toggle='wy-nav-shift']").removeClass("shift");
		$("[data-toggle='rst-versions']").toggleClass("shift");
    });
    $(document).on('click', "[data-toggle='rst-current-version']", function() {
		$("[data-toggle='rst-versions']").toggleClass("shift-up");
    });
    $("table.docutils:not(.field-list)").wrap("<div class='wy-table-responsive'></div>");
	$('#text-table-of-contents ul').first().addClass('nav');
    $('body').scrollspy({target: '#text-table-of-contents'});
    $('table').stickyTableHeaders();
    var $postamble = $('#postamble');
	var views = $('<p><span id="busuanzi_container_site_pv">Views: <span id="busuanzi_value_site_pv"></span></span></p>');
	$postamble.append(views);
    var $tableOfContents = $('#table-of-contents');
    $tableOfContents.css({paddingBottom: $postamble.outerHeight()});
	var toggleBtn = $('<div class="toggle-table" attr="0" onclick="toggleTable()" ><</div>');
	$('#content').append(toggleBtn);
	var scrollBtn = $('<div class="scroll-top" onclick="scroll()">^</div>');
	$('#content').append(scrollBtn);
	$( window ).scroll(function() {
		if ($(this).scrollTop() >= 400) {
			$(".scroll-top").show();
		} else {
			$(".scroll-top").hide();
		}
	});
	scrollBtn.click(function () {
		$("html,body").animate({ scrollTop: 0 }, 1000);
	});
	var commentsDiv = $('<section id="isso-thread"></section>');
	$('#content').append(commentsDiv);
	$search = $('<input class="search-input" type="search" placeholder="搜索">');
	$('#text-table-of-contents').prepend($search);
	$search.on('input', function() {
		debounce(inputSearch, 300, $search);
	});
});
window.SphinxRtdTheme = (function (jquery) {
    var stickyNav = (function () {
        var navBar,
            win,
            stickyNavCssClass = 'stickynav',
            applyStickNav = function () {
                if (navBar.height() <= win.height()) {
                    navBar.addClass(stickyNavCssClass);
                } else {
                    navBar.removeClass(stickyNavCssClass);
                }
            },
            enable = function () {
                applyStickNav();
                win.on('resize', applyStickNav);
            },
            init = function () {
                navBar = jquery('nav.wy-nav-side:first');
                win    = jquery(window);
            };
        jquery(init);
        return {
            enable : enable
        };
    }());
    return {
        StickyNav : stickyNav
    };
}($));
function toggleTable() {
	if ($(".toggle-table").attr("attr") == "0") {
		$(".toggle-table").attr("attr", "1");
		$(".toggle-table").text(">");
		$("#postamble").animate({left: "-300px"}, 300);
		$("#table-of-contents").animate({left: "-300px"}, 300);
		$(".toggle-table").animate({left: "0px"}, 300);
		$("#content").animate({marginLeft: "0px"}, 300);
	} else {
		$(".toggle-table").attr("attr", "0");
		$(".toggle-table").text("<");
		$("#postamble").animate({left: "0px"}, 300);
		$("#table-of-contents").animate({left: "0px"}, 300);
		$(".toggle-table").animate({left: "300px"}, 300);
		$("#content").animate({marginLeft: "300px"}, 300);
	}
}

function inputSearch() {
	var search = $.trim($(this).val()).toLowerCase();
	tableSearch($('#text-table-of-contents ul'), search);
}

function tableSearch($ul, search) {
	$ul.find('li').each(function() {
		var name = $(this).find('a').text();
		if (name !== '') {
			if (name.toLowerCase().indexOf(search) === -1) {
				$(this).addClass('d-none');
				$(this).removeClass('active');
			} else {
				$(this).removeClass('d-none');
				$(this).addClass('active');
			}
		}
		tableSearch($(this).find('ul'), search);
		if (search === '') {
			$(this).removeClass('active');
		}
	});
}

function debounce(method, delay, context) {
	var args = arguments; 
	clearTimeout(method.timer);
	method.timer = setTimeout(function () {
		method.apply(context, args);
	}, delay);
}