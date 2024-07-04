package kr.or.ddit.comm.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.http.Part;

import kr.or.ddit.comm.dao.AtchFileDaoImpl;
import kr.or.ddit.comm.dao.IAtchFileDao;
import kr.or.ddit.comm.vo.AtchFileDetailVO;
import kr.or.ddit.comm.vo.AtchFileVO;

public class AtchFileServiceImpl implements IAtchFileService{
	
private static IAtchFileService fileService = new AtchFileServiceImpl();
	
	private IAtchFileDao fileDao;
	
	private AtchFileServiceImpl() {
		fileDao = AtchFileDaoImpl.getInstance();
	}
	
	public static IAtchFileService getInstance() {
		return fileService;
	}
	
	

	@Override
	public AtchFileVO saveAtchFileList(Collection<Part> parts) {
		
		
		String uploadPath = "d:/D_Other/upload_files";		  
		File uploadDIR = new File(uploadPath);
		if(!uploadDIR.exists()) {
			uploadDIR.mkdir();
		}
		
		AtchFileVO atchFileVO = null;
		
		boolean isFirstFile = true; // 첫 번째 파일 여부...
		
		for(Part part : parts) {
			
			String fileName = part.getSubmittedFileName(); // 업로드 파일명 추출
			
			if(fileName != null && !fileName.equals("")) {
				
				if(isFirstFile) { // 첫 번째 업로드파일인지 체크...
					
					isFirstFile = false;
					
					atchFileVO = new AtchFileVO();
					
					fileDao.insertAtchFile(atchFileVO); // ATCH_FILE 에 insert 하기
					
					
				}
				
				long fileSize = part.getSize(); // 파일크기
				String saveFileName = UUID.randomUUID().toString().replace("-", ""); // 저장파일명
				String saveFilePath = uploadPath + "/" + saveFileName; // 저장파일경로
				
				// 확장자 추출
				String fileExtension = fileName.lastIndexOf(".") < 0 ? " "
						: fileName.substring(fileName.lastIndexOf(".") + 1);
				
				
				try {
					// 업로드 파일 저장하기
					part.write(saveFilePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
								
				AtchFileDetailVO atchFileDetailVO = new AtchFileDetailVO();
				atchFileDetailVO.setAtchFileId(atchFileVO.getAtchFileId());
				atchFileDetailVO.setStreFileNm(saveFileName);
				atchFileDetailVO.setFileSize(fileSize);
				atchFileDetailVO.setOrignlFileNm(fileName);
				atchFileDetailVO.setFileStreCours(saveFilePath);
				atchFileDetailVO.setFileExtsn(fileExtension);
				
				fileDao.insertAtchFileDetail(atchFileDetailVO); // 파일세부정보 저장
				
				try {
					// 임시업로더 파일 삭제
					part.delete();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}		
		}
		
		return atchFileVO;
	}

	@Override
	public AtchFileVO getAtchFile(AtchFileVO fileVO) {
		
		return fileDao.getAtchFile(fileVO);
	}

	@Override
	public AtchFileDetailVO getAtchFileDetail(AtchFileDetailVO atchFileDetailVO) {
		
		return fileDao.getAtchFileDetail(atchFileDetailVO);
	}
	
	
	
	public void daoTest() {
		
		boolean isFirstFile = true;
		
		AtchFileVO atchFileVO = null;
		
		if(isFirstFile) { // 첫 번째 업로드파일인지 체크...
			
			isFirstFile = false;
			
			atchFileVO = new AtchFileVO();
			
			fileDao.insertAtchFile(atchFileVO); // ATCH_FILE 에 insert 하기
			
			
		}
		
		String uploadPath = "d:/D_Other/upload_files";	
		
		String fileName = "abc.jpg";
		
		long fileSize = 10000; // 파일크기
		String saveFileName = UUID.randomUUID().toString().replace("-", ""); // 저장파일명
		String saveFilePath = uploadPath + "/" + saveFileName; // 저장파일경로
		
		// 확장자 추출
		String fileExtension = fileName.lastIndexOf(".") < 0 ? " "
				: fileName.substring(fileName.lastIndexOf(".") + 1);
		
						
		AtchFileDetailVO atchFileDetailVO = new AtchFileDetailVO();
		atchFileDetailVO.setAtchFileId(atchFileVO.getAtchFileId());
		atchFileDetailVO.setStreFileNm(saveFileName);
		atchFileDetailVO.setFileSize(fileSize);
		atchFileDetailVO.setOrignlFileNm(fileName);
		atchFileDetailVO.setFileStreCours(saveFilePath);
		atchFileDetailVO.setFileExtsn(fileExtension);
		
		fileDao.insertAtchFileDetail(atchFileDetailVO); // 파일세부정보 저장
		
		System.out.println("성공!!");
		
		
	}
	
	public static void main(String[] args) {
		
		new AtchFileServiceImpl().daoTest();
	}

}
