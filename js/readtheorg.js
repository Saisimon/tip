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

    // Shift nav in mobile when clicking the menu.
    $(document).on('click', "[data-toggle='wy-nav-top']", function() {
      $("[data-toggle='wy-nav-shift']").toggleClass("shift");
      $("[data-toggle='rst-versions']").toggleClass("shift");
    });
    // Close menu when you click a link.
    $(document).on('click', ".wy-menu-vertical .current ul li a", function() {
      $("[data-toggle='wy-nav-shift']").removeClass("shift");
      $("[data-toggle='rst-versions']").toggleClass("shift");
    });
    $(document).on('click', "[data-toggle='rst-current-version']", function() {
      $("[data-toggle='rst-versions']").toggleClass("shift-up");
    });
    // Make tables responsive
    $("table.docutils:not(.field-list)").wrap("<div class='wy-table-responsive'></div>");
});

$( document ).ready(function() {
    $('#text-table-of-contents ul').first().addClass('nav');
                                        // ScrollSpy also requires that we use
                                        // a Bootstrap nav component.
    $('body').scrollspy({target: '#text-table-of-contents'});

    // add sticky table headers
    $('table').stickyTableHeaders();

    // set the height of tableOfContents
    var $postamble = $('#postamble');
    var $tableOfContents = $('#table-of-contents');
    $tableOfContents.css({paddingBottom: $postamble.outerHeight()});

    // add TOC button
    var toggleSidebar = $('<div id="toggle-sidebar"><a href="#table-of-contents"><h2>目录</h2></a></div>');
    $('#content').prepend(toggleSidebar);

    // add close button when sidebar showed in mobile screen
    var closeBtn = $('<a class="close-sidebar" href="#">Close</a>');
    var tocTitle = $('#table-of-contents').find('h2');
	tocTitle.text("目录");
    tocTitle.append(closeBtn);
	
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