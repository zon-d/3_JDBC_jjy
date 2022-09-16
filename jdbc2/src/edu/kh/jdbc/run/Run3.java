package edu.kh.jdbc.run;

import java.util.Scanner;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run3 {

	public static void main(String[] args) {
		
		// 번호, 제목, 내용을 입력받아
		// 번호가 일치하는 행의 제목, 내용 수정
		
		// 수정 성공 시 -> 수정되었습니다.
		// 수정 실패 시 -> 일치하는 번호가 없습니다.
		// 예외 발생 시 -> 수정 중 예외가 발생했습니다.
		
		Scanner sc = new Scanner(System.in);
		
		TestService service = new TestService();
	
		System.out.print("수정할 번호 입력 : ");
		int input = sc.nextInt();
		
		System.out.print("수정할 제목 입력 : ");
		String input2 = sc.next();
		
		System.out.print("수정할 내용 입력 : ");
		String input3 = sc.next();
		
		
		TestVO vo = new TestVO(input, input2, input3);
		
		try {
			int result = service.update(vo);
			
			if(result > 0) {
				System.out.println("수정 성공");
			}else {
				System.out.println("실패...");
			}
		}catch(Exception e) {
			System.out.println("수정 중 예외 발생");
			e.printStackTrace();
		}
		
		
		
		
		
	}

	private static TestVO TestVO(int input, String input2, String input3) {
		// TODO Auto-generated method stub
		return null;
	}
}
