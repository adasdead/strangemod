package com.beetw.examplemod.block;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация-маркер, которая требуется, чтобы регистратору предметов показать, что блоку нужен свой предмет.
 * Пропиши её перед классом, если хочешь, чтобы у блока был свой предмет после регистрации
 *
 * <pre>
 * <b>@ModBlockItem</b>
 * public class BlockName extends ModBlock ...
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ModBlockItem {
}
