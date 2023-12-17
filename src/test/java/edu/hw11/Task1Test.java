package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    public void task1test() throws Exception {
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString")).intercept(FixedValue.value("Hello World!"))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        assertEquals(dynamicType.getDeclaredConstructor().newInstance().toString(), "Hello World!");
    }

    class ArithmeticUtils {
        public static int sum(int a, int b) {
            return a + b;
        }
    }

    class MyArithmeticUtils {
        public static int sum(int a, int b) {
            return a * b;
        }
    }

    @Test
    public void task2test() {
        ByteBuddyAgent.install();
        new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(named("sum"))
            .intercept(MethodDelegation.to(MyArithmeticUtils.class))
            .make()
            .load(
                ArithmeticUtils.class.getClassLoader(),
                ClassReloadingStrategy.fromInstalledAgent()
            );

        ArithmeticUtils arithmeticUtils = new ArithmeticUtils();

        assertEquals(arithmeticUtils.sum(2, 3), 6);
    }


    @Test
    void task3test() {

        MethodVisitor mv = new MethodVisitor();
        mv.visitVarInsn(Opcodes.ILOAD,1);
        mv.visitJumpInsn(Opcodes.IFEQ,...);

        mv.visitInsn(Opcodes.LRETURN);



    }
}
