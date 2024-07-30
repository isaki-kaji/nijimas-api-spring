package com.nijimas.api.core.constant;

import jakarta.validation.constraints.Pattern;

public class RegexpConstants {

    public static final String MAIN_CATEGORY_PATTERN = "food|hobbies|fashion|goods|essentials|travel|entertainment|transport|other";

    public static final String UUID_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

    public static final String PUBLIC_TYPE_NO_PATTERN = "[123]";
}
