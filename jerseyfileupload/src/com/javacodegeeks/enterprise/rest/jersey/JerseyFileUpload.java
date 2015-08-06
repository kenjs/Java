package com.javacodegeeks.enterprise.rest.jersey;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/files")
public class JerseyFileUpload {
	
	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "d://我的文档/Downloads/upload_files";
	
	/**
	 * upload file
	 * @param fileInputStream
	 * @param contentDispositionHeader
	 * @return
	 */
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
						@FormDataParam("file") InputStream fileInputStream,
						@FormDataParam("file") FormDataContentDisposition contentDispositionHeader
						) {
		String filePath = SERVER_UPLOAD_LOCATION_FOLDER + contentDispositionHeader.getFileName();
		//save the file to the server
		saveFile(fileInputStream, filePath);
		
		String output = "File saved to server location: " + filePath;
		
		return Response.status(200).entity(output).build();
	}
	
	private void saveFile(InputStream uploadedInputStream,
            String serverLocation) {
		try {
			OutputStream outStream = new FileOutputStream(serverLocation);
			int read = 0;
			byte[] bytes = new byte[1024];
			while((read = uploadedInputStream.read(bytes))!= -1){
				outStream.write(bytes,0,read);				
			}
			outStream.flush();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
