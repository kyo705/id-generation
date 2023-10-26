package org.idgeneration;

import org.assertj.core.api.Assertions;
import org.idgeneration.idgenerator.IdGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;


class IdGenerationApplicationTests {

	@Test
	void testConcurrency() {

		// given
		Set<Long> idSet = new HashSet<>();

		IdGenerator idGenerator = new IdGenerator(1, System.currentTimeMillis());

		// when
		Runnable backgroundThread = () -> {
			for(int i=0;i<100000;i++){
				idSet.add(idGenerator.generateId());
			}
		};
		backgroundThread.run();

		for(int i=0;i<100000;i++){
			idSet.add(idGenerator.generateId());
		}

		Assertions.assertThat(idSet.size()).isEqualTo(200000);
	}

	@Test
	void testCreationCntPerSecond() {

		// given
		long requiredCreationCntPerSecond = 10000;
		long second = 1000;
		IdGenerator idGenerator = new IdGenerator(1, System.currentTimeMillis());

		long startTimestamp = System.currentTimeMillis();
		long cnt = 0L;
		while(System.currentTimeMillis() - startTimestamp < second) {
			idGenerator.generateId();
			cnt++;
		}
		Assertions.assertThat(cnt).isGreaterThan(requiredCreationCntPerSecond);
		System.out.println("초당 id 생성 횟수 : " + cnt + "개");
	}
}
