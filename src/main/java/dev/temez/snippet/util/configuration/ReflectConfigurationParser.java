package dev.temez.snippet.util.configuration;


import dev.temez.snippet.util.configuration.annotation.ConfigurationPath;
import dev.temez.snippet.util.configuration.exception.IncomparableFieldType;
import dev.temez.snippet.util.configuration.exception.NoSuchConfigurationSection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.bukkit.configuration.file.FileConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

/**
 * @author temez
 * @since 0.1dev
 */
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ReflectConfigurationParser<T> {

    final FileConfiguration fileConfiguration;
    @Getter
    T configuration;

    /**
     * На основе полей класса парсит файл конфигурации и загружает из него значения
     *
     * @throws NoSuchConfigurationSection
     * @throws IncomparableFieldType
     */
    @SneakyThrows({InstantiationException.class, IllegalAccessException.class})
    public ReflectConfigurationParser<T> parse() throws NoSuchConfigurationSection, IncomparableFieldType {
        configuration = (T) configuration.getClass().newInstance();
        List<Field> fields = Arrays.stream(configuration.getClass().getDeclaredFields()).toList();
        for (Field field : fields) {
            String pathString = "";
            ConfigurationPath pathAnnotation = field.getAnnotation(ConfigurationPath.class);
            if (pathAnnotation != null) {
                pathString = pathAnnotation.path() + ".";
                if (pathAnnotation.key().length() != 0) {
                    pathString += pathAnnotation.key();
                } else {
                    pathString += field.getName();
                }
            }
            if (!fileConfiguration.isSet(pathString)) {
                throw new NoSuchConfigurationSection(field.getName(), fileConfiguration.getName());
            }
            field.setAccessible(true);
            if (field.getType().isAssignableFrom(Integer.class)) {
                field.set(configuration, fileConfiguration.getInt(pathString));
            } else if (field.getType().isAssignableFrom(Boolean.class)) {
                field.set(configuration, fileConfiguration.getBoolean(pathString));
            } else if (field.getType().isAssignableFrom(Double.class)) {
                field.set(configuration, fileConfiguration.getDouble(pathString));
            } else if (field.getType().isAssignableFrom(String.class)) {
                field.set(configuration, fileConfiguration.getString(pathString));
            } else if (field.getType().isAssignableFrom(List.class)) {
                ParameterizedType parametrizedType = (ParameterizedType) field.getGenericType();
                if (parametrizedType.getActualTypeArguments()[0].toString().contains("Integer")) {
                    field.set(configuration, fileConfiguration.getIntegerList(pathString));
                } else if (parametrizedType.getActualTypeArguments()[0].toString().contains("Double")) {
                    field.set(configuration, fileConfiguration.getDoubleList(pathString));
                } else if (parametrizedType.getActualTypeArguments()[0].toString().contains("String")) {
                    field.set(configuration, fileConfiguration.getStringList(pathString));
                } else throw new IncomparableFieldType(field.getName());
            } else throw new IncomparableFieldType(field.getName());
        }
        return this;
    }
}
