package com.osource.base.form.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZIPUtil {
	private static ZIPUtil zu = null;

	public void CreateZipFile(String filePath, String zipFilePath) {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipFilePath);
			zos = new ZipOutputStream(fos);
			writeZipFile(new File(filePath), zos, "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				if (zos != null)
					zos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (zos != null)
					zos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void writeZipFile(File f, ZipOutputStream zos, String hiberarchy) {
		if (f.exists())
			if (f.isDirectory()) {
				hiberarchy = hiberarchy + f.getName() + "/";
				File[] fif = f.listFiles();
				for (int i = 0; i < fif.length; i++)
					writeZipFile(fif[i], zos, hiberarchy);
			} else {
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(f);
					ZipEntry ze = new ZipEntry(hiberarchy + f.getName());
					zos.putNextEntry(ze);
					int b;
					while ((b = fis.read()) != -1) {
						zos.write(b);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					try {
						if (fis != null)
							fis.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
					try {
						if (fis != null)
							fis.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} finally {
					try {
						if (fis != null)
							fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	}

	public static ZIPUtil getInstance() {
		if (zu == null)
			zu = new ZIPUtil();
		return zu;
	}

	public static void main(String[] args) {
		getInstance().CreateZipFile("D:/My Documents/jquery", "d:/test.zip");
	}
}