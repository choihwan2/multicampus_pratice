package programmers.school.day03;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

class WordChangeTest {

	@Test
	void test() {
		WordChange2 wordChange = new WordChange2();
		Assert.assertEquals(4, wordChange.solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"}));
		Assert.assertEquals(0, wordChange.solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log"}));
	}

}
