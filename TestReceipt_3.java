package Test220426;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestReceipt_3 {

	public static void main(String[] args) {
		// 영수증 출력 3 , 이마트

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat form = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss");
		String nowTime = form.format(cal.getTime());
		DecimalFormat df = new DecimalFormat("###,###,###,###,###");

		String[] k11_itemName = { "삼양식품(주) 불닭짬뽕 140g*4입", "[냉동][태국] 노브랜드 냉동 새우살 (31-40) (450g)", "[국산의힘] 고흥 미역 150g",
				"GAP 죽장사과 4~6입/봉 (1.3kg내외)", "[CJ] 고메너겟 550g", "[CJ] 비비고왕교자1.12kg", "일렉트로맨 UHD TV(E55KL7701)",
				"[서울우유] 서울우유 2.3L", "노브랜드 별미총각김치 1.5kg", "[피코크] 샤브샤브 요리재료 870g", "[피코크X도우룸] 까르보나라 파스타 679g",
				"샘표 쓱쓱싹싹 밥도둑 메추리알장조림 150g", "G)피코크 초코칩쿠키 클래식 150g", "노브랜드 감자칩 오리지날 110g", "크라운 국희땅콩샌드 372g(12입)",
				"[해태] 홈런볼초코 5번들 230g", "청우)쫀득초코칩240g", "롯데 카스타드 2번들 460G(20입)", "오리온 촉촉한초코칩 12P+4P 320G",
				"친환경 가계 절약 양파 1kg", "크리넥스 3겹 데코&소프트 30m*30롤", "메디안 치석치약 오리지널 120G*3입", "[피코크] 미니크로와상300g(생지)",
				"노브랜드 피넛버터 크리미 340g", "삼립 옛날밤만쥬400g", "사양벌꿀 1.5kg", "CJ백설매실청310ml", "마시는프로틴 14입 오리지널",
				"노브랜드 국산콩 콩나물 300g", "세척당근(봉)", "오뚜기 진라면 매운맛 120g*5입" };
		int[] k11_price = { 4480, 12800, 4280, 7900, 8980, 9980, 5490000, 6350, 11780, 9980, 13800, 2980, 1480, 890,
				3840, 5980, 4980, 6980, 3840, 3480, 23478, 9900, 5980, 2980, 3980, 12800, 4980, 12800, 1280, 3980,
				3100 };
		int[] k11_amount = { 2, 1, 3, 1, 2, 4, 2, 1, 5, 1, 2, 1, 5, 8, 1, 2, 3, 3, 4, 1, 1, 10, 3, 3, 1, 1, 2, 1, 2, 6,
				2 };
		// 면세 제품들 : 미가공식료품 등(식용으로 제공되는 농,축,수,임산물을 포함)
		boolean[] k11_taxFree = { false, false, true, true, false, false, false, true, false, false, false, false,
				false, false, false, false, false, false, false, true, false, false, false, false, false, false, false,
				false, true, true, false };

		for (int i = 0; i < k11_itemName.length; i++) {
			if (k11_taxFree[i] == true) {
				k11_itemName[i] = "* " + k11_itemName[i];
			} else {
				k11_itemName[i] = "  " + k11_itemName[i];
			}
			
			
			if(k11_itemName[i].getBytes().length <= 17) {
				while (k11_itemName[i].getBytes().length < 17) {
					k11_itemName[i] += " ";
				}
			} else {

				int cnt = 0;
				String k11_newName = "";
				for(char ch : k11_itemName[i].toCharArray()) {
					
					cnt += String.valueOf(ch).getBytes().length;
					if(cnt > 17) 
						break;
					k11_newName += ch;

				}
				k11_itemName[i] = k11_newName;
				if(k11_itemName[i].getBytes().length <= 17) {
					while (k11_itemName[i].getBytes().length < 17) {
						k11_itemName[i] += " ";
					}
				}
			}
				
		}

		
		
		int k11_sumTaxFree = 0;
		int k11_sumPrice = 0;
		

		System.out.println(" emart       이마트 죽전점 (031)888-1234");
		System.out.println(" emart       206-86-50913 강희석");
		System.out.println(" emart       용인 수지구 포은대로 552");
		System.out.println("영수증 미지참시 교환/환불 불가");
		System.out.println("정상상품에 한함, 30일 이내(신선 7일)");
		System.out.println("※ 일부 브랜드매장 제외(매장 고지물참조)");
		System.out.println("교환/환불 구매점에서 가능(결제카드 지참)\n");
		System.out.printf("[구매] %s pos:%s\n", nowTime, "0011-9861");
		System.out.println("----------------------------------------");

		System.out.printf("%6.10s%16.4s%3.2s%6.8s\n", "상 품 명", "단 가", "수량", "금 액");
		System.out.println("----------------------------------------");

		for (int index = 0; index < k11_itemName.length; index++) {
			System.out.printf("%8s%9.9s%3.2s%11.10s\n", k11_itemName[index], df.format(k11_price[index]),
					df.format(k11_amount[index]), df.format(k11_price[index] * k11_amount[index]));
			
			if(k11_taxFree[index] == true) {
				k11_sumTaxFree += k11_price[index] * k11_amount[index];
			} else {
				k11_sumPrice += k11_price[index] * k11_amount[index];
			}
			

			if ((index + 1) % 5 == 0) {
				System.out.println("----------------------------------------");
			}
		}
		System.out.println();
		System.out.printf("          총 품목 수량          %8s\n", df.format(k11_itemName.length));
		System.out.printf("       (*)면 세  물 품          %8s\n", df.format(k11_sumTaxFree));
		//과세 버림
		System.out.printf("          과 세  합 계        %8s\n", df.format( k11_sumPrice * 10 / 11));
		//부가세 올림
		System.out.printf("          부   가   세         %8s\n", df.format(k11_sumPrice / 11 + 1));
		System.out.printf("          합       계         %8s\n", df.format(k11_sumPrice));
		System.out.printf("결제대상금액                  %9s\n", df.format(k11_sumPrice));
		System.out.println("----------------------------------------");
		System.out.println("0012 KEB 하나      541707**0484/35860658");
		System.out.printf("카드결제(IC)         일시불 / %9s\n", df.format(k11_sumPrice) );
		System.out.println("         [신세계포인트 적립]               ");
		System.out.println("김*언 고객님의 포인트 현황입니다.");
		System.out.println("금회발생포인트");
		System.out.println("누계(가용)포인트");
		System.out.println("*신세계포인트 유효기간은 2년입니다.");
		System.out.println("----------------------------------------");
		System.out.println("      구매금액기준 무료주차시간 자동부여");
		System.out.printf("차량번호 :                      %7s\n", "34저****");
		System.out.println("입차시간 :           " + nowTime);
		System.out.println("----------------------------------------");
		System.out.println("캐셔:084599 양OO                    1150");
		System.out.println("  |||||||||||||||||||||||||||||||||||||  ");
		System.out.println("  |||||||||||||||||||||||||||||||||||||  ");
		System.out.println("     20210303/00119861/00164980/31");
	}
}
