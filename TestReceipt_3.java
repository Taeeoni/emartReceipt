package Test220426;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestReceipt_3 {

	public static void main(String[] args) {
		// 영수증 출력 3 , 이마트
		
		//년월 시간을 찍는 포멧이 총 3개가 필요하다.
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat form = new SimpleDateFormat("YYYY-MM-dd HH:mm");// 년월시간 포멧 1
		String nowTime = form.format(cal.getTime()); // 현재시간 포멧 1 
		SimpleDateFormat form2 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"); // 년월시간 포멧 2
		String nowTime2 = form2.format(cal.getTime()); // 현재시간 포멧 2
		SimpleDateFormat form3 = new SimpleDateFormat("YYYYMMdd"); // 년월시간 포멧 3
		String nowTime3 = form3.format(cal.getTime()); // 현재시간 포멧 3

		DecimalFormat df = new DecimalFormat("###,###,###,###,###"); // 숫자에 콤마를 붙여주는 DecimalFormat 객체 호출 
		
		// 구매한 아이템들의 이름 배열 
		String[] k11_itemName = { "삼양식품(주) 불닭짬뽕 140g*4입" , "[냉동][태국] 노브랜드 냉동 새우살 (31-40) (450g)", "[국산의힘] 고흥 미역 150g",
				"GAP 죽장사과 4~6입/봉 (1.3kg내외)", "[CJ] 고메너겟 550g", "[CJ] 비비고왕교자1.12kg", "일렉트로맨 UHD TV(E55KL7701)",
				"[서울우유] 서울우유 2.3L", "노브랜드 별미총각김치 1.5kg", "[피코크] 샤브샤브 요리재료 870g", "[피코크X도우룸] 까르보나라 파스타 679g",
				"샘표 쓱쓱싹싹 밥도둑 메추리알장조림 150g", "G)피코크 초코칩쿠키 클래식 150g", "노브랜드 감자칩 오리지날 110g", "크라운 국희땅콩샌드 372g(12입)",
				"[해태] 홈런볼초코 5번들 230g", "청우)쫀득초코칩240g", "롯데 카스타드 2번들 460G(20입)", "오리온 촉촉한초코칩 12P+4P 320G",
				"친환경 가계 절약 양파 1kg", "크리넥스 3겹 데코&소프트 30m*30롤", "메디안 치석치약 오리지널 120G*3입", "[피코크] 미니크로와상300g(생지)",
				"노브랜드 피넛버터 크리미 340g", "삼립 옛날밤만쥬400g", "사양벌꿀 1.5kg", "CJ백설매실청310ml", "마시는프로틴 14입 오리지널",
				"노브랜드 국산콩 콩나물 300g", "세척당근(봉)", "오뚜기 진라면 매운맛 120g*5입" };
		// 구매한 아이템들의 가격 배열
		int[] k11_price = { 4480, 12800, 4280, 7900, 8980, 9980, 15490000, 6350, 11780, 9980, 13800, 2980, 1480, 890,
				3840, 5980, 4980, 6980, 3840, 3480, 23478, 9900, 5980, 2980, 3980, 12800, 4980, 12800, 1280, 3980,
				3100 };
		// 구매한 아이템들의 수량 배열
		int[] k11_amount = { 5, 1, 3, 1, 2, 4, 2, 1, 5, 1, 2, 1, 5, 8, 15, 2, 3, 3, 4, 1, 1, 10, 3, 3, 1, 1, 2, 1, 2, 6,
				2 };
		// 면세 제품들 : 미가공식료품 등(식용으로 제공되는 농,축,수,임산물을 포함)
		// 면세제품인지 아닌지 boolean 배열
		boolean[] k11_taxFree = { false, false, true, true, false, false, false, true, false, false, false, false,
				false, false, false, false, false, false, false, true, false, false, false, false, false, false, false,
				false, true, true, false };

		for (int i = 0; i < k11_itemName.length; i++) { // 상품명 변환하기 위한 반복문 , 물품이름 배열 길이 만큼 반복 
			
			if (k11_taxFree[i] == true) { // 만약 면세 제품이라면?
				k11_itemName[i] = "* " + k11_itemName[i]; // 앞에다가 *을 붙여준다.
			} else { // 면세 제품이 아니라면 k11_taxFree[i] == false
				k11_itemName[i] = "  " + k11_itemName[i]; // 앞에다가 빈칸을 붙여준다.
			}
			
			if(k11_itemName[i].getBytes().length <= 17) { // 상품명의 byte가 15이하라면 
				while (k11_itemName[i].getBytes().length < 17) { // 상품명의 byte가 15가 될때까지 반복
					k11_itemName[i] += " "; // 상품명의 byte 가 15가 될때까지 빈칸을 붙여준다.
				}
			} else { // 상품명의 byte가 15 초과라면 
				//상품명을 15byte 만큼 잘라서 변경한다.
				//하지만 15byte 부분이 한글이라면 깨진글자가 들어가게 된다.
				//따라서 한글자씩 검사하면서 더해주고 글자가 더해졌을때 15byte가 초과된다면 글자를 안더해주게끔 로직

				int k11_byteCount = 0;
				String k11_newName = "";
				for(char ch : k11_itemName[i].toCharArray()) { //상품명을 char 단위로 한글자씩 쪼개서 Array를 만들어 준다. -> char ch
					
					k11_byteCount += String.valueOf(ch).getBytes().length; //한글자(char)의 byte길이를 계산 후 k11_byteCount 에 넣어준다.
					if(k11_byteCount > 17) //만약 k11_byteCount 가 15가 넘어가 버리면 
						break; // break를 해주어서 뒤에 오는 합치는 과정을 하지 않게 해준다.
					k11_newName += ch; // byte가 15가 될때까지 합친다.

				}

				k11_itemName[i] = k11_newName; // 알맞게 자른 상품명을 다시 원래 변수(k11_itemName[i])에 넣어준다.
				// 15byte 부분에 한글이 있는 상품명은 그 앞에서 잘라줬으므로 상품명의 byte가 15byte보다 낮을것이다.
				if(k11_itemName[i].getBytes().length <= 17) { // 그래서 15byte 보다 낮다면
					while (k11_itemName[i].getBytes().length < 17) {
						k11_itemName[i] += " "; // 15byte가 될때까지 빈칸을 붙여주는것을 반복 
					}
				}
			}
				
		}

		int k11_sumTaxFree = 0; // 면세제품들에 대한 합계를 넣어줄 변수 생성
		int k11_sumPrice = 0; // 과세제품들에 대한 합계를 넣어줄 변수 생성
		
		// 가게에 대한 정보를 출력해주는 부분
		System.out.println(" emart        이마트 죽전점 (031)888-1234");
		System.out.println(" emart        206-86-50913 강희석");
		System.out.println(" emart        용인 수지구 포은대로 552");
		System.out.println();
		System.out.println("영수증 미지참시 교환/환불 불가");
		System.out.println("정상상품에 한함, 30일 이내(신선 7일)");
		System.out.println("※일부 브랜드매장 제외(매장 고지물참조)");
		System.out.println("교환/환불 구매점에서 가능(결제카드 지참)\n");
		System.out.printf("[구 매] %s    POS:%s\n", nowTime, "0011-9861");
		System.out.println("-----------------------------------------");
		// 구매한 상품의 정보에 대한 subject 를 출력 
		System.out.printf("%7.10s%13.4s%4.2s%8.7s\n", "상 품 명", "단 가", "수량", "금 액"); 
		System.out.println("-----------------------------------------");

		for (int index = 0; index < k11_itemName.length; index++) { // 아이템들의 배열에 들어있는 원소 수만큼 for 반복
			// 아이템들의 상품명, 단가, 수량, 금액에 해당하는 부분을 반복출력 
			System.out.printf("%9s%10.10s%3.2s%11.10s\n", k11_itemName[index], df.format(k11_price[index]),
					df.format(k11_amount[index]), df.format(k11_price[index] * k11_amount[index]));
			
			//면세제품인지 확인 
			if(k11_taxFree[index] == true) { // 면세제품이 맞다면 
				k11_sumTaxFree += k11_price[index] * k11_amount[index]; // 면세제품 합계 변수에 계속 더해준다.
			} else { // 면세제품이 아니라면
				k11_sumPrice += k11_price[index] * k11_amount[index]; // 과세제품 합계 변수에 계속 더해준다.
			}
			
			// 아이템 5개마다 구분칸을 찍어주기 위한 if문
			if ((index + 1) % 5 == 0) { // 5단위마다 출력 하기 위해서 5로 나눈 나머지가 0 이면 
				System.out.println("-----------------------------------------"); // 구분선 출력하게끔 한다.
			}
		}
		
		int k11_VAT = 0; // 부가세에 대한 변수 생성
		
		//부가세에 대한 값을 계산하기 위한 if문
		if( k11_sumPrice != 0) { // 과세 물품이 있다면 
			k11_VAT = k11_sumPrice / 11 + 1; // 총 과세물품들의 합계금액에서 11을 나눠주고 세금에 대한 부분은 +1원하여서 올림처리 
		}
		
		
		
		System.out.println();
		//구매한 수량들의 합 = 물품이름들 배열의 길이
		System.out.printf("               총 품목 수량   %11s\n", df.format(k11_itemName.length));
		// 면세 물품들의 합 금액 
		System.out.printf("            (*)면 세  물 품   %11s\n", df.format(k11_sumTaxFree));
		//과세 버림
		System.out.printf("               과 세  물 품   %11s\n", df.format( k11_sumPrice * 10 / 11));
		//부가세 올림
		System.out.printf("               부   가   세   %11s\n", df.format(k11_VAT));
		// 면세 물품 + 과세물품 + 부가세 출력
		System.out.printf("               합        계   %11s\n", df.format(k11_sumPrice + k11_sumTaxFree));
		// 결제 대상 금액 = 합계
		System.out.printf("결 제 대 상 금 액             %11s\n", df.format(k11_sumPrice + k11_sumTaxFree));
		System.out.println("-----------------------------------------");
		//카드 결제에 대한 정보
		System.out.println("0012 KEB 하나       541707**0484/35860658");
		System.out.printf("카드결제(IC)         일시불 / %11s\n", df.format(k11_sumPrice + k11_sumTaxFree));
		System.out.println("-----------------------------------------");
		// 포인트 적립에 대한 정보
		System.out.println("           [신세계포인트 적립]");
		System.out.printf("%s 고객님의 포인트 현황입니다.\n", "김*언");
		System.out.printf("금회발생포인트        %10s%9.5s\n", "9350**9995", "164");
		System.out.printf("누계(가용)포인트   %10s(%10s)\n", df.format(5637), df.format(5473));
		System.out.println("*신세계포인트 유효기간은 2년입니다.");
		System.out.println("-----------------------------------------");
		//주차에 대한 정보 
		System.out.println("   구매금액기준 무료주차시간 자동부여");
		System.out.printf("차량번호 :                       %7s\n", "34저****");
		System.out.println("입차시간 :            " + nowTime2);
		System.out.println("-----------------------------------------");
		//캐셔 정보 
		System.out.println("캐셔:084599 양OO                     1150");
		System.out.println("   |||||||||||||||||||||||||||||||||||||  ");
		System.out.println("   |||||||||||||||||||||||||||||||||||||  ");
		// 영수증 발급 날짜 + 바코드 
		System.out.println("       " + nowTime3 + "/00119861/00164980/31");
	}
}
