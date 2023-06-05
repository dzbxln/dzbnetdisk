package com.dengzebin.netdisk;

import com.dengzebin.netdisk.service.FolderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class NetdiskApplicationTests {

	@Resource
	private FolderService folderService;

	@Test
	void contextLoads() {
	}

}
