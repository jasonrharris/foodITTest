<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" uri="http://threewks.com/thundr/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" lang="en-us"/>
    <t:style src="bootstrap.min.css"/>
    <t:style src="font-awesome.min.css"/>
    <t:script src="jquery-1.9.0.min.js"/>
</head>
<body>
<header class="container">
    <h1>FOODit Test</h1>
</header>
<section class="container">
    <h2>Load Data</h2>

    <p>
        To start the test you need to click the load data button to setup the restaurant configuration data.</p>

    <div><a class="btn btn-primary" href="/load/">Load Data</a></div>

    <p>
        This will load json data for following four restaurants:</p>
    <ul>
        <li><a href="/restaurant/bbqgrill/download">bbqgrill</a></li>
        <li><a href="/restaurant/butlersthaicafe/download">butlersthaicafe</a></li>
        <li><a href=/restaurant/jashanexquisiteindianfood/download>jashanexquisiteindianfood</a></li>
        <li><a href="/restaurant/newchinaexpress/download">newchinaexpress</a></li>
    </ul>
    <p>The data includes the menu and existing orders that have been previously placed for the restaurant <code>RestaurantLoadData.java</code>
    </p>
    <h2>Order Data</h2>
        <h3>Orders per restaurant:</h3>

        <p>
        <ul>
            <li><a href="/restaurant/bbqgrill/orders/count">bbqgrill</a></li>
            <li><a href="/restaurant/butlersthaicafe/orders/count">butlersthaicafe</a></li>
            <li><a href="/restaurant/jashanexquisiteindianfood/orders/count">jashanexquisiteindianfood</a></li>
            <li><a href="/restaurant/newchinaexpress/orders/count">newchinaexpress</a></li>
        </ul>

    <h3>Sales per restaurant:</h3>


    <ul>
        <li><a href="/restaurant/bbqgrill/sales">bbqgrill</a></li>
        <li><a href="/restaurant/butlersthaicafe/sales">butlersthaicafe</a></li>
        <li><a href="/restaurant/jashanexquisiteindianfood/sales">jashanexquisiteindianfood</a></li>
        <li><a href="/restaurant/newchinaexpress/sales">newchinaexpress</a></li>
    </ul>


    <h3>Most Popular Category per restaurant:</h3>


    <ul>
        <li><a href="/restaurant/bbqgrill/mostPopularCategory">bbqgrill</a></li>
        <li><a href="/restaurant/butlersthaicafe/mostPopularCategory">butlersthaicafe</a></li>
        <li><a href="/restaurant/jashanexquisiteindianfood/mostPopularCategory">jashanexquisiteindianfood</a></li>
        <li><a href="/restaurant/newchinaexpress/mostPopularCategory">newchinaexpress</a></li>
    </ul>

    <h3>Most Popular Item By Restaurant:</h3>

    <p>
    <a href="/platform/mostFrequentItems"><ul><li>Most Popular by Restaurant</li></ul></a></p>



</section>