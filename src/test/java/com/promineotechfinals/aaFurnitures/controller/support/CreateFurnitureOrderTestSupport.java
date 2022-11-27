package com.promineotechfinals.aaFurnitures.controller.support;

public class CreateFurnitureOrderTestSupport extends BaseTest {
	/**
	 * 
	 * @return
	 */
	protected String createOrderBody() {
		//@formatter:off
		return "{\n"
				+"  \"customer\":\"JAMES_PAUL\",\n"
				+"  \"room\":\"BED_ROOM\",\n"
				+"  \"material\":\"Wood\",\n"
				+"  \"color\":\"Dark_Finish\",\n"
				+"  \"options\":[\n"
				+"  \"AA_Collections\",\n"
				+"  \"NISH_Collections\",\n"
				+"  \"ZURI_Collections\"\n"
				+" ]\n"
				+"}";
		//@formatter:on
	
	}

}
