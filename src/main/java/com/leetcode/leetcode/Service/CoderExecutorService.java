package com.leetcode.leetcode.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.leetcode.leetcode.Pojo.Enums.Language;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CoderExecutorService {

    public CoderExecutorService() {

    }

    public  String  runCode(Language  language,  String  code,String input) {
        String  filename   = "Main." + getFilextension(language);
        saveFile(filename, code);
        String  runcommand   =  getCommand(language);

        return  executeCommand(runcommand, input);
    }

    private  String executeCommand(String  command, String  input) {
        log.info("Inout  Recivedd "+ input);
         StringBuilder  sb  =  new  StringBuilder();
        try { //  this  we  cna  hvae  about  to  create the   Sandbox an  rjn  that  code
            ProcessBuilder  pb =  new ProcessBuilder("/bin/sh", "-c" , command);
            pb.redirectErrorStream(true);
            Process  process   =  pb.start();
            Thread   thread   =  new  Thread( () -> {
                try(BufferedWriter  writer   = new  BufferedWriter(new  OutputStreamWriter(process.getOutputStream()))) {
                      
                    for(String   ch  :  input.split(",")) {
                        writer.write(ch);
                        writer.newLine();
                    }
                  
                    writer.flush();

                } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                try (BufferedReader  reader =  new BufferedReader(new  InputStreamReader(process.getInputStream()))){
                    String  line;
                    while((line  = reader.readLine())  != null) {
                    // line  =  reader.readLine();
                       // System.out.println("Output  from th e  File "+line);
                       //log.info("Output  form  the   User  COde  "+line);   
                        sb.append(line).append("\n");
                    }
                
                
                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } 
            });
            thread.start();
            boolean found  =  process.waitFor(10, TimeUnit.SECONDS); //  gvae  to10  chaneg  harcode  
            if(!found) {
                process.destroy();
                System.out.println("Process  Destroyed TLE  ERROE");
                thread.interrupt(); // Stop safely using a flag
 
                return  "TLE ERROR";
            }
            thread.join();
            
        }
        catch(Exception  e) {

        }
       // System.out.println(sb.toString());
       log.info("FInal  Output  recived"+sb.toString());
       return  sb.toString().trim();
    }

    private  String getFilextension(Language language ){
        switch (language) {
            case Java:
                return "java";
            case Cpp:
                return "cpp";
            case  Python:
                return "py";
            case JavaScript:
                return "js";
            default:
                return ".txt";
        }
    }
    private  String  getCommand(Language language) {
       if(language.equals(Language.Java)){
        return  "javac Main.java &&  java Main";
       }
       else if(language.equals(Language.Cpp)) {
        return  "g++ Main.cpp -o Main &&  ./Main";
       }
       else if(language.equals(Language.Python) ) {
        return  "python3 Main.py";
       }
       else {
        return "node  Main.js"; 
       }
    } 

    private  void  saveFile(String  filename  ,String  code) {
        try(FileWriter  writer   =  new FileWriter(filename)) {
            writer.write(code);
            log.info(filename+" Save  Succesfully  ");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //  Code  Filaed  to  Run  Try  again  After  SOome  Time ;
        }
        
    }


}
