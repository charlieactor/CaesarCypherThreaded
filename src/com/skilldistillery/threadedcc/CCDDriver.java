package com.skilldistillery.threadedcc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CCDDriver {
	static ExecutorService ex = Executors.newCachedThreadPool();

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Map<Integer, String> decrypted = new HashMap<>();

		String bullshit = "P SLHYULK AV WYVNYHT HA AOL ZRPSS KPZAPSSLYF";
		List<Future<String>> futureList = new ArrayList<>();

		for (int i = 0; i < 26; i++) {
			Future<String> result = ex.submit(new CaesarCypherDecrypter(bullshit, i));
			futureList.add(result);
		}
		ex.shutdown();
		int i = 0;
		for (Future<String> future : futureList) {
			decrypted.put(i, future.get());
			i++;
		}
		Set<Integer> keys = decrypted.keySet();
		Iterator<Integer> it = keys.iterator();

		BufferedReader bufIn = null;
		List<String> everyWordEver = new ArrayList<>();
		try {
			bufIn = new BufferedReader(new FileReader("Dictionary.txt"));

			String line;
			while ((line = bufIn.readLine()) != null) {
				String fields = line;
				everyWordEver.add(fields.toUpperCase());
			}
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (bufIn != null) {
				try {
					bufIn.close();
				} catch (IOException e) {
					System.err.println(e);
				}
			}
		}

		while (it.hasNext()) {
			Integer thisKey = it.next();
			String[] sentences = decrypted.get(thisKey).split(" ");
			INNER: for (int j = 0; j < sentences.length; j++) {
				if (everyWordEver.contains(sentences[j])) {
					if (j > 2) {
						for (int k = 0; k < sentences.length; k++) {
							System.out.print(sentences[k] + " ");
						}
						System.out.println("\nYour key is (probably) " + thisKey);
						break INNER;
					}
				}
				else {
					break INNER;
				}
			}
		}
	}
}
