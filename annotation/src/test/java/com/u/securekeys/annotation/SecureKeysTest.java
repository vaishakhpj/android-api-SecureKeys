package com.u.securekeys.annotation;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Created by saguilera on 6/16/17.
 */
@SecureKeys({
    @SecureKey(key = "test_key_1", value = "test_key_1"),
    @SecureKey(key = "test_key_2", value = "test_key_2"),
    @SecureKey(key = "test_key_3", value = "test_key_3")
})
public class SecureKeysTest {

    /**
     * Test we can generate the clss from the fully-qualified name correctly.
     * This is crucial since the annotation processor is hooked via the fully-qualified name,
     * if its not a correct one, it wont work
     */
    @Test
    public void test_ClasspathCorrectlyGeneratesClass() {
        try {
            Class<?> clazz = Class.forName(SecureKeys.CLASSPATH);
            Assert.assertTrue(clazz.isAnnotation());
            Assert.assertEquals(clazz, SecureKeys.class);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test the annotation doesnt get to runtime, if it does then its a major security flaw.
     */
    @Test
    public void test_AnnotationDoesntGetRetainedForRuntime() {
        Assert.assertFalse(SecureKeysTest.class.isAnnotationPresent(SecureKeys.class));
    }

}
