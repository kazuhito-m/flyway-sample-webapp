package com.github.kazuhito_m.sample.config;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SettingsTest {
	@Test
	public void 設定クラスと対をなすプロパティファイルをリソースのディレクトリに有ればセットしたBeanを取得できる() {
		// act
		Settings actual = Settings.get();
		// assertion
		assertThat(actual.getJdbcDriver(), is("com.mysql.jdbc.Driver"));
        assertThat(actual.getJdbcUser(), is("sample"));
	}
}
