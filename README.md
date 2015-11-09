# Mondrian
It is a java project of random Mondrian creator. 

What you can get:
  1. Random Mondrian images with random grids and colors.
  2. It will fill your whole browser, and you can resize your brower to get different sizes of Mondrian.

How to run:
  1. You need environment of JDK 1.6+, maven 2+, Tomcat (or other containers) plugin in eclipse.
  2. Run mvn eclipse:eclipse in the root directory of this source code.
  3. Import into your eclipse and run as a webapp with your Tomcat in eclipse.
  4. Visit URL: http://{host}:{port}{/context}/background (e.g. http://localhost:8089/demo-web/background) will show the dynamic   image, refresh to get a new one
  5. If you want to get the restful data behind a dynamic Mondrian, visit URL:     http://{host}:{port}{/context}/mondrian/{x_pixel}_{y_pixel}.json (e.g. http://localhost:8089/demo-web/mondrian/1000_1000.json)

