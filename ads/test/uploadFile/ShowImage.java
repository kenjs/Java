package uploadFile;


public class ShowImage{

	/**
	 *
	 */
	private static final long serialVersionUID = 1902509131618884017L;

//	public String exec() {
//		String imgPath = request.getParameter("file");
//		File file = new File(imgPath);//ImgPath是图像文件的路径
//		try {
//			if (file.exists()) {
//				response.reset();
//				response.setContentType("image/jpeg");
//				response.setContentLength((int) file.length());
//				FileInputStream fIS = new FileInputStream(file);
//				ServletOutputStream servletOut = response.getOutputStream();
//
//				byte[] buf = new byte[1024];
//				int iRead = 0;
//				while (true) {
//					iRead = fIS.read(buf);
//					if (iRead > 0) {
//						servletOut.write(buf, 0, iRead);
//					} else
//						break;
//				}
//				fIS.close();
//				servletOut.flush();
//				servletOut.close();
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
