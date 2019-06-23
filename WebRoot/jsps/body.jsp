<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        *{
            padding: 0;
            margin: 0;
        }
        li,ul,a {
            list-style: none;
            text-decoration: none;
        }
        .clearfix::after {
            content: "";
            display: block;
            clear: both;
        }
        .wrap {
            position: relative;
            width: 840px;
            height: 1200px;
            margin-left: 35px;
            margin-top: 15px;
        }
        .img-ct {
            position: absolute;
        }
        .img-ct img {
            width: 840px;
            height: 1200px;
        }
        .img-ct li {
            position: absolute;
           display: none;
        }
        .btn {
            position: absolute;
            display: block;
            background-color: rgba(0,0,0,0.5);
            width: 30px;
            height: 30px;
            text-align: center;
            line-height: 30px;
            border-radius: 15px;
            top: 50%;
            transform: translate(0,-50%);
            color: white;
        }
        .btn-pre {
            margin-left: 10px;
        }
        .btn-next {
            right: 10px;
        }
        .white-ct {
            position: absolute;
            bottom: 10px;
            left: 10px;
            right: 10px;
            text-align: center;
        }
        .white {
            display: inline-block;
        }
        .white li {
            width: 20px;
            height: 6px;
            display: inline-block;
            background-color: white;
            border-radius: 4px;
            margin-left: 5px;
        }
        .white .active {
            background-color: #666;
        }

    </style>
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
<div class="wrap">
    <ul class="img-ct clearfix">


        <li data-page="0"><a href=""><img src="<c:url value='/images/3.jpg'/>"></a></li>
        <li data-page="1"><a href=""><img src="<c:url value='/images/2.jpg'/>"></a></li>
        <li data-page="2"><a href=""><img src="<c:url value='/images/1.jpg'/>"></a></li>
        <li data-page="3"><a href=""><img src="<c:url value='/images/4.jpg'/>"></a></li>
    </ul>
    <a href="" class="btn btn-pre">&lt;</a>
    <a href="" class="btn btn-next">&gt;</a>
    <div class="white-ct ">
        <ul class="white">
            <li class="active"></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</div>
<script>

    var pageIndex = 0;
    var imgLength = $(".img-ct li").length;
    var isLoad = true;
    var $items = $(".img-ct").children();
    interval();
    play(0);
    $(".btn-next").on("click",function (e) {
        e.preventDefault();
        if(isLoad){
            nextBtn(1);
        }
    });
    $(".btn-pre").on("click",function (e) {
        e.preventDefault();
        if(isLoad){
            preBtn(1);
        }
    });
    $(".white").on("click","li",function () {
        var index = $(this).index();
        var number = index - pageIndex;
        console.log(index);
        if(number>0){
            nextBtn(number);
        }else if(number<0){
            preBtn(-number);
        }else{
            console.log("gg")
        }
        bullet(index);
    });
    function nextBtn(n) {
        play((pageIndex+n)%imgLength);
    }
    function preBtn(n) {
        play((pageIndex-n)%imgLength);
    }
    function play(n) {
        isLoad = false;
        $items.eq(pageIndex).fadeOut(500);
        $items.eq(n).fadeIn(500,function () {
            isLoad = true;
        });
        pageIndex = n;
        bullet(n);
    }
    function bullet(e) {
        $(".white li").removeClass("active");
        $(".white li").eq(e).addClass("active");
    }
    function interval() {
        setInterval(function () {
            nextBtn(1);
        },4000)
    }


</script>
</body>
</html>
