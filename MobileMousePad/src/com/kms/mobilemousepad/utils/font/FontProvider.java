package com.kms.mobilemousepad.utils.font;

import java.awt.Font;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 본 프로그램에서 사용하는 폰트를 제공하는 클래스
 * @author Kwon
 *
 */
public class FontProvider {
	private static final FontProvider INSTANCE = new FontProvider();
	
	private final String THIS_CLASS_PATH = FontProvider.class.getResource( "./" ).getPath();
	private final Map<FontType, Map<Float, Font>> FONT_MAP = new HashMap<>();
	private final float DEFAULT_SIZE = 15.0f;
	
	private FontProvider() {
		Font font = null;
		for(FontType fontType : FontType.values()) {
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, new File(THIS_CLASS_PATH + fontType.FILE_NAME))
						.deriveFont(Font.BOLD).deriveFont(DEFAULT_SIZE);
			} catch(Exception e) {
				font = new Font(null, Font.PLAIN, (int)DEFAULT_SIZE);
			}
			FONT_MAP.put(fontType, new HashMap<>());
			FONT_MAP.get(fontType).put(DEFAULT_SIZE, font);
		}
	}
	/**
	 * 기본 크기의 폰트를 반환하는 매서드
	 * @param fontType 폰트의 타입
	 * @return 기본 사이즈의 fontType에 맞는 폰트를 반환
	 */
	public static Font getFont(FontType fontType) {
		return getFont(fontType, INSTANCE.DEFAULT_SIZE);
	}
	/**
	 * 크기를 지정한 폰트를 반환하는 매서드
	 * @param fontType 폰트의 타입
	 * @param fontSize 폰트 사이즈
	 * @return fontSize의 fontType에 맞는 폰트를 반환
	 */
	public static Font getFont(FontType fontType, float fontSize) {
		if(!INSTANCE.FONT_MAP.get(fontType).containsKey(fontSize))
			INSTANCE.FONT_MAP.get(fontType).put(fontSize, INSTANCE.FONT_MAP.get(fontType).get(INSTANCE.DEFAULT_SIZE).deriveFont(fontSize));
		
		return INSTANCE.FONT_MAP.get(fontType).get(fontSize);
	}
}
