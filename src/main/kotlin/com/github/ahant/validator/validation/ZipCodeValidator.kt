package com.github.ahant.validator.validation

import com.github.ahant.validator.constants.ApplicationConstants.INDIA_ZIP_CODE_REGEX
import com.github.ahant.validator.constants.ApplicationConstants.US_ZIP_CODE_REGEX
import com.github.ahant.validator.validation.util.CountryCode
import com.google.common.collect.ImmutableMap

/**
 * Created by ahant on 8/14/2016.
 */
internal class ZipCodeValidator(regEx: String, msg: String, maxLength: Int = 0, minLength: Int = 0) : StringFieldValidator(regEx, msg, maxLength, minLength) {

    override fun validate(input: Any): Set<String> {
        return super.validate(input);
    }

    override fun setCountry(countryCode: CountryCode) {
        if (countryZipCodeMap.contains(countryCode)) {
            this.setRegEx(countryZipCodeMap.get(countryCode).toString())
        }
    }

    private val countryZipCodeMap = ImmutableMap.builder<CountryCode, String>().put(CountryCode.INDIA, INDIA_ZIP_CODE_REGEX)
            .put(CountryCode.US, US_ZIP_CODE_REGEX).build()

}
