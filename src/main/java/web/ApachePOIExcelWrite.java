package web;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import model.Comment;
import model.Comments;
import model.Favoris;
import model.XingProfile;

public class ApachePOIExcelWrite {

	
	
//
//	@SuppressWarnings("unchecked")
//	List<Favoris> favoris = (List<Favoris>) model.get("favoris");
//	if(favoris!=null)
//	{
//		// create excel xls sheet
//		Sheet sheet = workbook.createSheet("Spring MVC AbstractXlsView");
//
//		// create header row
//		Row header = sheet.createRow(0);
//		header.createCell(0).setCellValue("Favoris");
//
//
//		// Create data cells
//		int rowCount = 1;
//		for (Favoris oneFavoris : favoris){
//			Row courseRow = sheet.createRow(rowCount++);
//
//
//			courseRow.createCell(0).setCellValue(oneFavoris.getTitle());
//			for(XingProfile profile : oneFavoris.getFavList())
//			{
//				courseRow.createCell(1).setCellValue(profile.getDisplayName());
//				courseRow.createCell(2).setCellValue(profile.getHaves());
//
//				for(Comments comments : profile.getComments())
//				{
//					for(Comment comment : comments.getCommentList())
//					{
//						courseRow.createCell(3).setCellValue(comment.getComment()+"<br style=\"mso-data-placement:same-cell;\">");
//					}
//				}
//
//
//			}
//
//			//courseRow.createCell(0).setCellValue(oneFavoris.getId());
//			//courseRow.createCell(1).setCellValue(oneFavoris.getName());
//			//courseRow.createCell(2).setCellValue(DATE_FORMAT.format(oneFavoris.getDate()));
//		}
//	}
	
}
