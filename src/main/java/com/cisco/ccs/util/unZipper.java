package com.cisco.ccs.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

public class unZipper {

	   private void unzipArchive(File archive, File outputDir) {
	        try {
	            ZipFile zipfile = new ZipFile(archive);
	            for (Enumeration e = zipfile.getEntries(); e.hasMoreElements(); ) {
	                ZipEntry entry = (ZipEntry) e.nextElement();
	                unzipEntry(zipfile, entry, outputDir);
	            }
	        } catch (Exception e) {
	            System.out.printf("Error extracting file " + archive, e);
	        }
	    }

	    private void unzipEntry(ZipFile zipfile, ZipEntry entry, File outputDir) throws IOException {


	        if (entry.isDirectory()) {
	            createDir(new File(outputDir, entry.getName()));
	            return;
	        }
	        
	        File outputFile = new File(outputDir, entry.getName());
	        if (!outputFile.getParentFile().exists()){
	            createDir(outputFile.getParentFile());
	        }

	        BufferedInputStream inputStream = new BufferedInputStream(zipfile.getInputStream((ZipArchiveEntry) entry));
	        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));

	        try {
	            IOUtils.copy(inputStream, outputStream);
	        } finally {
	            outputStream.close();
	            inputStream.close();
	        }
	    }

	    private void createDir(File dir) {
	        if(!dir.mkdirs()) throw new RuntimeException("Can not create dir "+dir);
	    }
	    
	public boolean epubUnzipper(File epubPath) {
		    String epubPathStr = epubPath.toString();
		    String destinationname = epubPathStr.substring(0, epubPathStr.lastIndexOf('.'));
           unzipArchive(epubPath, new File(destinationname));
		return true;
	}
	

	public boolean epubUnzipperStream(String epubPathStr)
	{
	    try
	    {
	        String destinationname = epubPathStr.substring(0, epubPathStr.lastIndexOf('.'));
	        //fileFolderPath = destinationname;
	        File folder = new File(destinationname);
	    	if(!folder.exists()){
	    		folder.mkdir();
	    	}else {
	    		return true;
	    	}
	        byte[] buf = new byte[1024];
	        ZipInputStream zipinputstream = null;
	        ZipEntry zipentry;
	        zipinputstream = new ZipInputStream(new FileInputStream(epubPathStr));
	
	        zipentry = zipinputstream.getNextEntry();
	        while (zipentry != null) 
	        { 
	            String entryName = zipentry.getName();
	            int n;
	            FileOutputStream fileoutputstream;
	            File newFile = new File(entryName);
	            String directory = newFile.getParent();
	            if(directory == null)
	            {
	                if(newFile.isDirectory())
	                    break;
	            }else {
	            	folder = new File(destinationname+"/"+directory);
	 	        	if(!folder.exists()){
	 	        		if(folder.mkdirs()) {
	// 	                    System.out.println("Directory Created");
	 	                }
	 	        	}
	            }
	             
	            String destinationname1 = destinationname+"/"+entryName;
	            fileoutputstream = new FileOutputStream(destinationname1);             
	
	            while ((n = zipinputstream.read(buf, 0, 1024)) > -1)
	                fileoutputstream.write(buf, 0, n);
	
	            fileoutputstream.close(); 
	            zipinputstream.closeEntry();
	            zipentry = zipinputstream.getNextEntry();
	        }
	        zipinputstream.close();
	    }
	    catch (Exception e)	{
	        e.printStackTrace();
	        return false;
	    }
	    return true;
	}


	
}
