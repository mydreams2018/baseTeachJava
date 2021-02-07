package juc;

import java.util.concurrent.Phaser;

public class MyPhaser extends Phaser{

	public MyPhaser(int parties) {
		super(parties);
	}

	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
		switch(phase) {
			case 0:
			System.out.println("开始");
			break;
			case 1:
				System.out.println("one");
				break;
			case 2:
				System.out.println("two");
				break;
			case 3:
				System.out.println("结束");
				return true;
		}
		return super.onAdvance(phase, registeredParties);
	}
	
}
