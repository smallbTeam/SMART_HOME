/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.message.service;

import org.springframework.stereotype.Service;

/**
 * @author ligw
 * @version $Id messageService.java, v 0.1 2017-07-15 15:57 ligw Exp $$
 */
public interface ShortMessageService {

        public Integer sendShortMessage(String mobelPhone, String msmContent);
}
