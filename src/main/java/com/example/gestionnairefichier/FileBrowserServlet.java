package com.example.gestionnairefichier;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class FileBrowserServlet extends HttpServlet {


    public List<File> ListeDesFichiers(String path) {
        List<File> result = new ArrayList<>();
        File repertoire = new File(path);

        // Vérifier si le chemin spécifié est un répertoire
        if (repertoire.isDirectory()) {
            File[] files = repertoire.listFiles();
            if (files != null) {
                System.out.println("pas vide");
                for (File file : files) {

                    result.add(file);
                }

            }
        } else {
            System.out.println("sorry");
        }
        return result;
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Obtention du chemin du répertoire à lister
        String path = "C:\\Users\\AGODA Marina\\Documents\\AGODA"; // Modifier avec votre chemin

        List<File> listes = ListeDesFichiers(path);
        request.setAttribute("liste",listes );

        /*for (File file:listes)
        {
            // Définir le type de contenu pour la réponse HTTP
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

            //lire le fichier et écrire dans la réponse http
            try(
                    FileInputStream f= new FileInputStream(file);
                    OutputStream os = response.getOutputStream())
            {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = f.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
            }

        }*/
        this.getServletContext().getRequestDispatcher("WEB-INF/index.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }


    public void destroy() {
    }
}