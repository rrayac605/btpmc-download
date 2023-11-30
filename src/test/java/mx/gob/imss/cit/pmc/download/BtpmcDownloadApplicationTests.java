package mx.gob.imss.cit.pmc.download;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import mx.gob.imss.cit.pmc.commons.dto.SftpParamsDTO;
import mx.gob.imss.cit.pmc.exception.DownloadException;
import mx.gob.imss.cit.pmc.service.SftpClient;

@SpringBootTest
class BtpmcDownloadApplicationTests {

	private final static Logger logger = LoggerFactory.getLogger(BtpmcDownloadApplicationTests.class);

	@Ignore
	void test1uploadFile() {
		assertNotNull(envio());
	}

	@Ignore
	void test2retriveFile() {
		assertNotNull(descarga());
	}

	Integer envio() {
		Integer retornar = null;

		SftpClient client = new SftpClient();

		SftpParamsDTO SftpParamsDTO = new SftpParamsDTO();

		SftpParamsDTO.setSftpHost("10.100.6.76");
		SftpParamsDTO.setSftpPort(22);
		SftpParamsDTO.setSftpPasword("PMC123");
		SftpParamsDTO.setSftpUser("usr_pmc");

		client.connect(SftpParamsDTO);

		try {
			String sourceFile = "c:\\PMC\\datos.txt";
			String destinationFile = "/nssa/datos.txt";

			client.uploadFile(sourceFile, destinationFile);
			retornar = 1;
		} catch (DownloadException e) {
			logger.error("No paso la carga del archivo");
			fail("No paso la carga del archivo", e);
			retornar = null;
		} finally {
			client.disconnect();
		}
		return retornar;
	}

	Integer descarga() {
		Integer retornar = null;
		SftpClient client = new SftpClient();

		SftpParamsDTO SftpParamsDTO = new SftpParamsDTO();

		SftpParamsDTO.setSftpHost("10.100.6.76");
		SftpParamsDTO.setSftpPort(22);
		SftpParamsDTO.setSftpPasword("PMC123");
		SftpParamsDTO.setSftpUser("usr_pmc");

		client.connect(SftpParamsDTO);

		try {
			String sourceFile = "/nssa/datos.txt";
			String destinationFile2 = "c:\\PMC\\datos2.txt";

			client.retrieveFile(sourceFile, destinationFile2);
			retornar = 1;
		} catch (DownloadException e) {
			logger.error("No paso la descarga del archivo");
			fail("No paso la descarga del archivo", e);
			retornar = null;
		} finally {
			client.disconnect();
		}
		return retornar;
	}

}
