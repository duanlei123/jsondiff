$(function () {
    var qcloud = {};
    $('[_t_nav]').hover(function () {
            var _nav = $(this).attr('_t_nav');
            clearTimeout(qcloud[_nav + '_timer']);
            qcloud[_nav + '_timer'] = setTimeout(function () {
                $('#' + _nav).stop(true, true).fadeIn(100);
            }, 0);
        },
        function () {
            var _nav = $(this).attr('_t_nav');
            clearTimeout(qcloud[_nav + '_timer']);
            qcloud[_nav + '_timer'] = setTimeout(function () {
                $('#' + _nav).stop(true, true).fadeOut(0);
            }, 100);
        });
    /*跳转*/
    var goTop = $('.gotop');
    goTop.fadeOut();
    $(window, document).scroll(function () {
        $(this).scrollTop() > 100 ? goTop.fadeIn() : goTop.fadeOut();
    });
    /*
     * 初始化按钮的loading功能，点击后将显示Loading字样，并且按钮被disabled掉，无法连续点击，防止二次提交
     * 超过3秒后按钮将恢复原状
     */
    $('button[data-loading-text]').click(function () {
        var btn = $(this).button('loading');
        setTimeout(function () {
            btn.button('reset');
        }, 3000);
    });
});
function copyallcode(id) {
    var Url2=document.getElementById(id);
    Url2.select(); // 选择对象
    document.execCommand("Copy"); // 执行浏览器复制命令
    layer.msg('复制成功');
}
function copyToClipboard(id) {
    selectText(id);
    document.execCommand("Copy"); // 执行浏览器复制命令
    layer.msg('复制成功');
}
function selectText(element) {
    var text = document.getElementById(element);
    if (document.body.createTextRange) {
        var range = document.body.createTextRange();
        range.moveToElementText(text);
        range.select();
    } else if (window.getSelection) {
        var selection = window.getSelection();
        var range = document.createRange();
        range.selectNodeContents(text);
        selection.removeAllRanges();
        selection.addRange(range);
        /*if(selection.setBaseAndExtent){
            selection.setBaseAndExtent(text, 0, text, 1);
        }*/
    } else {
        layer.msg("无内容");
    }
}