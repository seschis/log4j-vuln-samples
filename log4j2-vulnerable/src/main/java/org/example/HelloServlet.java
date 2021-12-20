package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/world")
public class HelloServlet extends HttpServlet {

  private static Logger logger = LogManager.getLogger();

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    var parm1 = req.getParameter("foo");
    var parm2 = req.getParameter("bar");

    logger.info("parm1 is " + parm1);  // log-injection
    var a = StringUtils.chop(parm1);

    var f = new File(a);
    try (var fos = new FileOutputStream(f)) {
      fos.write("Hello World".getBytes(StandardCharsets.UTF_8));
    }
  }

  private String myfunc1(String a) {
    return a;
  }

  private String myfunc2(String a, String b) {
    return a + b;
  }

  private String myfunc3(String a) {
    return a.toLowerCase();
  }
}
