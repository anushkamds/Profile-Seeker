<%-- 
    Document   : index
    Created on : Aug 30, 2014, 11:39:03 PM
    Author     : Anushka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile Seeker</title>
        <meta name="description" content="Blueprint: Split Layout" />
        <meta name="keywords" content="website template, layout, css3, transition, effect, split, dual, two sides, portfolio" />
        <meta name="author" content="Codrops" />
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/component.css" />
        <link rel="stylesheet" type="text/css" href="css/spinner.css" />
        <script src="js/modernizr.custom.js"></script>
        <script src="js/canvasjs.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="js/submitFunction.js"></script>
    </head>
    <body>
        <div class="container">
            <div id="splitlayout" class="splitlayout">
                <div class="intro">
                    <div class="side side-right">
                        <div class="input-box" style="position: relative; top:5%;">
                            <form id="form1">
                                <input type="text" id="user" class="input-text" placeholder="Enter Name"/>
                                <input type="button" id="submit" value="Search" />
                                <br/>
                            </form>
                        </div>
                        <div class="intro-content">                            
                        </div>
                        <div class="overlay"></div>
                          <div class="footer" style="position: relative; top: 75%">

                            <p>Data Collection Sites</p>

                            <img class="footer-images" src="img/github.png">

                            <img class="footer-images" src="img/linkedIn.png">

                            <img class="footer-images" src="img/googlescholar.png">

                        </div>
                    </div>
                </div>
                <!-- /intro -->
                <div class="page page-right">
                    <div class="page-inner">                        
                    </div>
                    <!-- /page-inner -->
                </div>
                <!-- /page-right -->
                <a href="#" class="back back-left" title="back to intro">&larr;</a>
            </div>
            <!-- /splitlayout -->
        </div>
        <!-- /container -->
        <script src="js/classie.js"></script>
        <script src="js/cbpSplitLayout.js"></script>
        <div class="spinner-background"></div>
        <div class="spinner"></div>
    </body>
</html>

