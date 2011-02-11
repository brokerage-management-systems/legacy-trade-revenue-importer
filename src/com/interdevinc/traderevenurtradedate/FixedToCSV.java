/*
 * DatToCSV
 * Created on Jul 29, 2009, 11:10 AM
 * @author Matthew Weppler
 * copyright 2009 InterDev Inc.
 *
 */
package com.interdevinc.traderevenurtradedate;

//TODO: This definition should be moved to a config file
public class FixedToCSV {

    private int numOfFields = 241;
    private int[] origFieldLengths = new int[numOfFields];
    private int[] calcFieldLengths = new int[numOfFields];
    private String[] fieldNames = new String[numOfFields];
    private String[][] fieldValues;
    char delim = ',';
    private String[] lineCSV;

    public int getNumOfFields() {
        return this.numOfFields;
    }

    public void setFieldLengths() {
        // Length of fixed width fields

        origFieldLengths[0] = 2; //length 2
        origFieldLengths[1] = 4; //length 7
        origFieldLengths[2] = 1; //length 9
        origFieldLengths[3] = 8; //length 18
        origFieldLengths[4] = 8; //length 27
        origFieldLengths[5] = 1; //length 29
        origFieldLengths[6] = 1; //length 31
        origFieldLengths[7] = 1; //length 33
        origFieldLengths[8] = 1; //length 35
        origFieldLengths[9] = 1; //length 37
        origFieldLengths[10] = 1; //length 39
        origFieldLengths[11] = 3; //length 43
        origFieldLengths[12] = 6; //length 50
        origFieldLengths[13] = 1; //length 52
        origFieldLengths[14] = 2; //length 55
        origFieldLengths[15] = 9; //length 65
        origFieldLengths[16] = 5; //length 71
        origFieldLengths[17] = 1; //length 73
        origFieldLengths[18] = 8; //length 82
        origFieldLengths[19] = 11; //length 94
        origFieldLengths[20] = 11; //length 106
        origFieldLengths[21] = 11; //length 118
        origFieldLengths[22] = 4; //length 123
        origFieldLengths[23] = 2; //length 126
        origFieldLengths[24] = 1; //length 128
        origFieldLengths[25] = 6; //length 135
        origFieldLengths[26] = 16; //length
        origFieldLengths[27] = 1; //length
        origFieldLengths[28] = 1; //length
        origFieldLengths[29] = 1; //length
        origFieldLengths[30] = 1; //length
        origFieldLengths[31] = 2; //length
        origFieldLengths[32] = 1; //length
        origFieldLengths[33] = 1; //length
        origFieldLengths[34] = 3; //length
        origFieldLengths[35] = 3; //length
        origFieldLengths[36] = 1; //length
        origFieldLengths[37] = 1;
        origFieldLengths[38] = 1;
        origFieldLengths[39] = 9;
        origFieldLengths[40] = 8;
        origFieldLengths[41] = 1;
        origFieldLengths[42] = 1;
        origFieldLengths[43] = 1;
        origFieldLengths[44] = 1;
        origFieldLengths[45] = 1;
        origFieldLengths[46] = 1;
        origFieldLengths[47] = 11;
        origFieldLengths[48] = 10;
        origFieldLengths[49] = 2;
        origFieldLengths[50] = 1;
        origFieldLengths[51] = 3;
        origFieldLengths[52] = 1;
        origFieldLengths[53] = 1;
        origFieldLengths[54] = 1;
        origFieldLengths[55] = 4;
        origFieldLengths[56] = 2;
        origFieldLengths[57] = 2;
        origFieldLengths[58] = 2;
        origFieldLengths[59] = 10;
        origFieldLengths[60] = 10;
        origFieldLengths[61] = 3;
        origFieldLengths[62] = 7;
        origFieldLengths[63] = 3;
        origFieldLengths[64] = 1;
        origFieldLengths[65] = 1;
        origFieldLengths[66] = 1;
        origFieldLengths[67] = 1;
        origFieldLengths[68] = 1;
        origFieldLengths[69] = 2;
        origFieldLengths[70] = 1;
        origFieldLengths[71] = 1;
        origFieldLengths[72] = 1;
        origFieldLengths[73] = 1;
        origFieldLengths[74] = 1;
        origFieldLengths[75] = 16;
        origFieldLengths[76] = 18;
        origFieldLengths[77] = 9;
        origFieldLengths[78] = 1;
        origFieldLengths[79] = 8;
        origFieldLengths[80] = 2;
        origFieldLengths[81] = 1;
        origFieldLengths[82] = 18;
        origFieldLengths[83] = 15;
        origFieldLengths[84] = 12;
        origFieldLengths[85] = 10;
        origFieldLengths[86] = 8;
        origFieldLengths[87] = 8;
        origFieldLengths[88] = 8;
        origFieldLengths[89] = 8;
        origFieldLengths[90] = 10;
        origFieldLengths[91] = 1;
        origFieldLengths[92] = 2;
        origFieldLengths[93] = 14;
        origFieldLengths[94] = 10;
        origFieldLengths[95] = 10;
        origFieldLengths[96] = 10;
        origFieldLengths[97] = 10;
        origFieldLengths[98] = 8;
        origFieldLengths[99] = 1; //length
        origFieldLengths[100] = 20;
        origFieldLengths[101] = 16;
        origFieldLengths[102] = 2;
        origFieldLengths[103] = 4;
        origFieldLengths[104] = 20;
        origFieldLengths[105] = 20;
        origFieldLengths[106] = 20;
        origFieldLengths[107] = 20;
        origFieldLengths[108] = 15;
        origFieldLengths[109] = 2;
        origFieldLengths[110] = 5;
        origFieldLengths[111] = 20;
        origFieldLengths[112] = 20;
        origFieldLengths[113] = 3;
        origFieldLengths[114] = 2;
        origFieldLengths[115] = 2;
        origFieldLengths[116] = 2;
        origFieldLengths[117] = 3;
        origFieldLengths[118] = 3;
        origFieldLengths[119] = 3;
        origFieldLengths[120] = 3;
        origFieldLengths[121] = 1;
        origFieldLengths[122] = 10;
        origFieldLengths[123] = 9;
        origFieldLengths[124] = 5;
        origFieldLengths[125] = 1;
        origFieldLengths[126] = 1;
        origFieldLengths[127] = 1;
        origFieldLengths[128] = 2;
        origFieldLengths[129] = 3;
        origFieldLengths[130] = 2;
        origFieldLengths[131] = 1;
        origFieldLengths[132] = 1;
        origFieldLengths[133] = 1;
        origFieldLengths[134] = 1;
        origFieldLengths[135] = 5;
        origFieldLengths[136] = 1;
        origFieldLengths[137] = 7;
        origFieldLengths[138] = 6;
        origFieldLengths[139] = 1;
        origFieldLengths[140] = 7;
        origFieldLengths[141] = 4;
        origFieldLengths[142] = 4;
        origFieldLengths[143] = 4;
        origFieldLengths[144] = 3;
        origFieldLengths[145] = 9;
        origFieldLengths[146] = 3;
        origFieldLengths[147] = 5;
        origFieldLengths[148] = 6;
        origFieldLengths[149] = 1;
        origFieldLengths[150] = 1;
        origFieldLengths[151] = 1;
        origFieldLengths[152] = 6;
        origFieldLengths[153] = 2;
        origFieldLengths[154] = 4;
        origFieldLengths[155] = 1;
        origFieldLengths[156] = 10;
        origFieldLengths[157] = 4;
        origFieldLengths[158] = 2;
        origFieldLengths[159] = 5;
        origFieldLengths[160] = 3;
        origFieldLengths[161] = 3;
        origFieldLengths[162] = 4;
        origFieldLengths[163] = 12;
        origFieldLengths[164] = 1;
        origFieldLengths[165] = 1;
        origFieldLengths[166] = 2;
        origFieldLengths[167] = 9;
        origFieldLengths[168] = 2;
        origFieldLengths[169] = 9;
        origFieldLengths[170] = 2;
        origFieldLengths[171] = 9;
        origFieldLengths[172] = 2;
        origFieldLengths[173] = 9;
        origFieldLengths[174] = 2;
        origFieldLengths[175] = 9;
        origFieldLengths[176] = 2;
        origFieldLengths[177] = 9;
        origFieldLengths[178] = 4;
        origFieldLengths[179] = 2;
        origFieldLengths[180] = 4;
        origFieldLengths[181] = 4;
        origFieldLengths[182] = 4;
        origFieldLengths[183] = 1;
        origFieldLengths[184] = 4;
        origFieldLengths[185] = 1;
        origFieldLengths[186] = 6;
        origFieldLengths[187] = 5;
        origFieldLengths[188] = 1;
        origFieldLengths[189] = 3;
        origFieldLengths[190] = 3;
        origFieldLengths[191] = 2;
        origFieldLengths[192] = 2;
        origFieldLengths[193] = 12;
        origFieldLengths[194] = 1;
        origFieldLengths[195] = 2;
        origFieldLengths[196] = 2;
        origFieldLengths[197] = 1;
        origFieldLengths[198] = 9;
        origFieldLengths[199] = 1;
        origFieldLengths[200] = 31;
        origFieldLengths[201] = 2;
        origFieldLengths[202] = 86;
        origFieldLengths[203] = 1;
        origFieldLengths[204] = 7;
        origFieldLengths[205] = 1;
        origFieldLengths[206] = 4;
        origFieldLengths[207] = 2;
        origFieldLengths[208] = 3;
        origFieldLengths[209] = 2;
        origFieldLengths[210] = 1;
        origFieldLengths[211] = 2;
        origFieldLengths[212] = 1;
        origFieldLengths[213] = 9;
        origFieldLengths[214] = 1;
        origFieldLengths[215] = 7;
        origFieldLengths[216] = 1;
        origFieldLengths[217] = 7;
        origFieldLengths[218] = 1;
        origFieldLengths[219] = 7;
        origFieldLengths[220] = 11;
        origFieldLengths[221] = 9;
        origFieldLengths[222] = 1;
        origFieldLengths[223] = 10;
        origFieldLengths[224] = 2;
        origFieldLengths[225] = 2;
        origFieldLengths[226] = 2;
        origFieldLengths[227] = 20;
        origFieldLengths[228] = 2;
        origFieldLengths[229] = 20;
        origFieldLengths[230] = 6;
        origFieldLengths[231] = 3;
        origFieldLengths[232] = 1;
        origFieldLengths[233] = 7;
        origFieldLengths[234] = 1;
        origFieldLengths[235] = 7;
        origFieldLengths[236] = 1;
        origFieldLengths[237] = 6;
        origFieldLengths[238] = 7;
        origFieldLengths[239] = 3;
        origFieldLengths[240] = 37;

        int value = 0;
        for (int a = 0; a < numOfFields; ++a) {
            if (value == 0) {
                value += origFieldLengths[a];
            } else {
                value += origFieldLengths[a] + 1;
            }
            this.calcFieldLengths[a] = value;
        }
    }

    public int[] getFieldLengths() {
        return this.calcFieldLengths;
    }

    public void setFieldNames() {
        fieldNames[0] = "RECORD_NUMBER_01";
        fieldNames[1] = "FIRM_01";
        fieldNames[2] = "BUY_SELL_CODE_01";
        fieldNames[3] = "TRADE_DATE_01";
        fieldNames[4] = "SETTLEMENT_DATE_01";
        fieldNames[5] = "MARKET_CODE_01";
        fieldNames[6] = "BLOTTER_CODE_01";
        fieldNames[7] = "CANCEL_CODE_01";
        fieldNames[8] = "STREET_SIDE_CODE_01";
        fieldNames[9] = "DUE_BILL_01";
        fieldNames[10] = "CORRECTION_CODE_01";
        fieldNames[11] = "BRANCH_01_a";
        fieldNames[12] = "ACCOUNT_NUMBER_01";
        fieldNames[13] = "ACCOUNT_TYPE_01";
        fieldNames[14] = "COUNTRY_CODE_01";
        fieldNames[15] = "CUSIP_01";
        fieldNames[16] = "FILLER_01_a";
        fieldNames[17] = "BASIS_PRICE_CODE_01";
        fieldNames[18] = "RUN_DATE_01";
        fieldNames[19] = "TRADE_REFERENCE_NUMBER_01";
        fieldNames[20] = "USER_REFERENCE_NUMBER_01";
        fieldNames[21] = "CANCELED_COMBINED_REFERENCE_01";
        fieldNames[22] = "BATCH_01_a";
        fieldNames[23] = "RECORD_NUMBER_02";
        fieldNames[24] = "BATCH_02_b";
        fieldNames[25] = "COUNT_02";
        fieldNames[26] = "SYMBOL_02";
        fieldNames[27] = "SECURITY_TYPE_02";
        fieldNames[28] = "SECURITY_TYPE_MODIFIER_02";
        fieldNames[29] = "SECURITY_TYPE_CALCULATION_02";
        fieldNames[30] = "CNS_CODE_02";
        fieldNames[31] = "PRIMARY_EXCHANGE_02";
        fieldNames[32] = "DTC_ELIGIBLITY_CODE_02";
        fieldNames[33] = "FOREIGN_CODE_02";
        fieldNames[34] = "REGISTERED_REP_ENTER_REP_02";
        fieldNames[35] = "STATE_COUNTRY_CODE_02";
        fieldNames[36] = "NY_TAX_02";
        fieldNames[37] = "SECURITIES_INSTRUCTIONS_02";
        fieldNames[38] = "SERVICE_02";
        fieldNames[39] = "PARENT_ACCOUNT_02";
        fieldNames[40] = "AGENCY_CODE_02";
        fieldNames[41] = "FILLER_02_b";
        fieldNames[42] = "MODE_DEL_02";
        fieldNames[43] = "PROCEEDS_INSTRUCTIONS_02";
        fieldNames[44] = "CASH_DIVIDEND_INSTRUCTIONS_02";
        fieldNames[45] = "SALES_PROD_02";
        fieldNames[46] = "TRADE_UNIT_02";
        fieldNames[47] = "DIF_PRINCIPAL_02";
        fieldNames[48] = "SHORT_NAME_02";
        fieldNames[49] = "ACCOUNT_CLASSIFICATION_02";
        fieldNames[50] = "CITIZEN_CODE_02";
        fieldNames[51] = "COUNTRY_OF_TAX_RESIDENCY_02";
        fieldNames[52] = "TRANSFER_LEGEND_CODE_02";
        fieldNames[53] = "MARKET_MAKER_CODE_02";
        fieldNames[54] = "RR_PENALTY_02";
        fieldNames[55] = "MINOR_EXECUTING_BROKER_02";
        fieldNames[56] = "MINOR_CLEARING_BROKER_02_a";
        fieldNames[57] = "RECORD_NUMBER_03";
        fieldNames[58] = "MINOR_CLEARING_BROKER_03_b";
        fieldNames[59] = "OFFSET_ACCOUNT_03";
        fieldNames[60] = "OFFSET_SHORTNAME_03";
        fieldNames[61] = "OFFSET_RR_03";
        fieldNames[62] = "OFFSET_COMMISSION_03";
        fieldNames[63] = "CONF_BR_OVERRIDE_03";
        fieldNames[64] = "SOURCE_03";
        fieldNames[65] = "TYPE_OF_ORDER_03";
        fieldNames[66] = "CONFIRMATION_PRINT_03";
        fieldNames[67] = "COMPARISON_PRINT_03";
        fieldNames[68] = "COMMISSION_ACCUMULATION_03";
        fieldNames[69] = "COMMISSION_SCHEDULE_03";
        fieldNames[70] = "BLOTTER_OVERRIDE_CODE_03";
        fieldNames[71] = "NSCC_CODE_03";
        fieldNames[72] = "TAX_ACCUMULATION_03";
        fieldNames[73] = "COMMISSION_CONCESSION_CODE_03_a";
        fieldNames[74] = "SUBSTITUTE_BLOTTER_03";
        fieldNames[75] = "QUANTITY_03";
        fieldNames[76] = "PRICE_03";
        fieldNames[77] = "ALPHAPRICE_DOLLAR_03";
        fieldNames[78] = "ALPHAPRICE_SPACE_03";
        fieldNames[79] = "ALPHAPRICE_FRACTION_03_a";
        fieldNames[80] = "RECORD_NUMBER_04";
        fieldNames[81] = "ALPHAPRICE_FRACTION_04_b";
        fieldNames[82] = "PLUS_MINUS_04";
        fieldNames[83] = "PRINCIPAL_04";
        fieldNames[84] = "ACCRUED_INTEREST_04";
        fieldNames[85] = "TRADE_COMMISSION_04";
        fieldNames[86] = "STATE_TAX_04";
        fieldNames[87] = "SEC_FEE_04";
        fieldNames[88] = "CERTIFICATE_FEE_04";
        fieldNames[89] = "POSTAGE_04";
        fieldNames[90] = "SERVICE_CHARGE_MISC_FEE_04";
        fieldNames[91] = "NET_04_a";
        fieldNames[92] = "RECORD_NUMBER_05";
        fieldNames[93] = "NET_05_b";
        fieldNames[94] = "BROKERAGE_05";
        fieldNames[95] = "TRADE_CONCESSION_05";
        fieldNames[96] = "OTHER_EXCHANGE_COMMISSION_05";
        fieldNames[97] = "STANDARD_COMMISSION_05";
        fieldNames[98] = "LIMIT_ORDER_CHARGE_05";
        fieldNames[99] = "NUMBER_OF_SECURITY_DESCRIPTION_LINES_05";
        fieldNames[100] = "SECURITY_DESCRIPTION_LINE_05_a";
        fieldNames[101] = "SECURITY_DESCRIPTION_LINE_05_b";
        fieldNames[102] = "RECORD_NUMBER_06";
        fieldNames[103] = "SECURITY_DESCRIPTION_LINE_06_c";
        fieldNames[104] = "SECURITY_DESCRIPTION_LINE_06_d";
        fieldNames[105] = "SECURITY_DESCRIPTION_LINE_06_e";
        fieldNames[106] = "SECURITY_DESCRIPTION_LINE_06_f";
        fieldNames[107] = "SECURITY_DESCRIPTION_LINE_06_g";
        fieldNames[108] = "SECURITY_DESCRIPTION_LINE_06_h";
        fieldNames[109] = "RECORD_NUMBER_07";
        fieldNames[110] = "SECURITY_DESCRIPTION_LINE_07_i";
        fieldNames[111] = "SECURITY_DESCRIPTION_LINE_07_j";
        fieldNames[112] = "SECURITY_DESCRIPTION_LINE_07_k";
        fieldNames[113] = "GROUP_CODE_07";
        fieldNames[114] = "TRADER_CODE_07";
        fieldNames[115] = "CONFIRM_LEGEND_CODE_07_a";
        fieldNames[116] = "CONFIRM_LEGEND_CODE_07_b";
        fieldNames[117] = "REGISTERED_REP_EXEC_REP_RR2_07";
        fieldNames[118] = "SECOND_RR_PERCENT_07";
        fieldNames[119] = "THIRD_RR_CODE_07";
        fieldNames[120] = "THIRD_RR_PERCENT_07";
        fieldNames[121] = "PROSPECTUS_ENCLOSED_07";
        fieldNames[122] = "COMMISSION_DISCOUNT_PRECENT_07";
        fieldNames[123] = "STRIKE_PRICE_07";
        fieldNames[124] = "DUE_BILL_MULTIPLIER_07";
        fieldNames[125] = "POSTAGE_CODE_07";
        fieldNames[126] = "COMMISSION_CONCESSION_CODE_07_b";
        fieldNames[127] = "COMMISSION_PREFERENCE_CODE_07";
        fieldNames[128] = "FILLER_07_d";
        fieldNames[129] = "FUND_LOAD_OVERRIDE_07_a";
        fieldNames[130] = "RECORD_NUMBER_08";
        fieldNames[131] = "FUND_LOAD_OVERRIDE_08_b";
        fieldNames[132] = "QUANTITY_TYPE_08";
        fieldNames[133] = "CONFIRM_LINE_NUMBER_08";
        fieldNames[134] = "EXCHANGE_LINE_NUMBER_08";
        fieldNames[135] = "YIELD_08";
        fieldNames[136] = "YIELD_TYPE_08";
        fieldNames[137] = "YIELD_DATE_08";
        fieldNames[138] = "YIELD_PRICE_08";
        fieldNames[139] = "TRADING_AWAY_CODE_08";
        fieldNames[140] = "FILLER_08_e";
        fieldNames[141] = "MAJOR_CLEARING_BROKER_08";
        fieldNames[142] = "MAJOR_EXECUTING_BROKER_08";
        fieldNames[143] = "EXECUTION_TIME_08";
        fieldNames[144] = "BRANCH_08_b";
        fieldNames[145] = "IRS_NO_08";
        fieldNames[146] = "FILLER_08_f";
        fieldNames[147] = "MARKET_PLACE_08";
        fieldNames[148] = "MARKET_SEQUENCE_08";
        fieldNames[149] = "MARKET_OVERRIDE_08";
        fieldNames[150] = "TIME_IN_FORCE_CODE_08";
        fieldNames[151] = "AUTO_EXEC_CODE_08";
        fieldNames[152] = "ISSUER_08";
        fieldNames[153] = "ISSUER_TYPE_08";
        fieldNames[154] = "BOND_TRADER_08";
        fieldNames[155] = "BOND_CLASS_CODE_08";
        fieldNames[156] = "ADDITIONAL_MARKUP_08";
        fieldNames[157] = "TERMINAL_ID_08";
        fieldNames[158] = "RECORD_NUMBER_09";
        fieldNames[159] = "SIGNON_REP_LOCATION_09";
        fieldNames[160] = "REGISTERED_REP_SIGNON_REP_09";
        fieldNames[161] = "REGISTERED_REP_OWNING_REP_RR_09";
        fieldNames[162] = "FUND_LOAD_PERCENT_09";
        fieldNames[163] = "PRODUCT_CODE_09";
        fieldNames[164] = "TRADING_FLAT_CODE_09";
        fieldNames[165] = "_12B1_09";
        fieldNames[166] = "ADDITIONAL_FEE_CODE_09_a";
        fieldNames[167] = "ADDITIONAL_FEE_AMOUNT_09_a";
        fieldNames[168] = "ADDITIONAL_FEE_CODE_09_b";
        fieldNames[169] = "ADDITIONAL_FEE_AMOUNT_09_b";
        fieldNames[170] = "ADDITIONAL_FEE_CODE_09_c";
        fieldNames[171] = "ADDITIONAL_FEE_AMOUNT_09_c";
        fieldNames[172] = "ADDITIONAL_FEE_CODE_09_d";
        fieldNames[173] = "ADDITIONAL_FEE_AMOUNT_09_d";
        fieldNames[174] = "ADDITIONAL_FEE_CODE_09_e";
        fieldNames[175] = "ADDITIONAL_FEE_AMOUNT_09_e";
        fieldNames[176] = "ADDITIONAL_FEE_CODE_09_f";
        fieldNames[177] = "ADDITIONAL_FEE_AMOUNT_09_f";
        fieldNames[178] = "INSTITUTIONAL_THIRD_PARTY_09";
        fieldNames[179] = "RECORD_NUMBER_10";
        fieldNames[180] = "INSTITUTIONAL_MISC1_10";
        fieldNames[181] = "INSTITUTIONAL_MISC2_10";
        fieldNames[182] = "INSTITUTIONAL_LOT_NUMBER_10";
        fieldNames[183] = "BORD_TORD_CODE_10";
        fieldNames[184] = "MUTUAL_FUND_DTC_NUMBER_10";
        fieldNames[185] = "FILLER_10_g";
        fieldNames[186] = "TRADE_ENTRY_10";
        fieldNames[187] = "ENTRY_SEQUENCE_NUMBER_10";
        fieldNames[188] = "SOLICITED_CODE_10";
        fieldNames[189] = "ELECTRONIC_TRADE_ID_10";
        fieldNames[190] = "ROLLUP_COUNT_10";
        fieldNames[191] = "CONFIRM_LEGEND_CODE_10_a";
        fieldNames[192] = "CONFIRM_LEGEND_CODE_10_b";
        fieldNames[193] = "RELATIONSHIP_ID_10";
        fieldNames[194] = "CAPACITY_CODE_10";
        fieldNames[195] = "CONFIRM_LEGEND_CODE_10_c";
        fieldNames[196] = "CONFIRM_LEGEND_CODE_10_d";
        fieldNames[197] = "ALTERNATIVE_INVESTMENT_CODE_10";
        fieldNames[198] = "EXPANDED_YIELD_10";
        fieldNames[199] = "EXPANDED_YIELD_SIGN_10";
        fieldNames[200] = "FILLER_10_h";
        fieldNames[201] = "RECORD_NUMBER_11";
        fieldNames[202] = "FILLER_11_i";
        fieldNames[203] = "REVENUE_CLEARING_CHARGE_SIGN_11";
        fieldNames[204] = "REVENUE_CLEARING_CHARGE_AMOUNT_11";
        fieldNames[205] = "REVENUE_MISCELLANEOUS_FEE_SIGN_11";
        fieldNames[206] = "REVENUE_MISCELLANEOUS_FEE_AMOUNT_11_a";
        fieldNames[207] = "RECORD_NUMBER_12";
        fieldNames[208] = "REVENUE_MISCELLANEOUS_FEE_AMOUNT_12_b";
        fieldNames[209] = "PRODUCT_LEVEL_12";
        fieldNames[210] = "CONCESSION_CODE_12";
        fieldNames[211] = "PURCHASE_TYPE_CODE_12";
        fieldNames[212] = "TRADE_DEFINITION_TYPE_12";
        fieldNames[213] = "TRADE_DEFINITION_TRADE_ID_12";
        fieldNames[214] = "REVENUE_COMMISSION_SIGN_12";
        fieldNames[215] = "REVENUE_COMMISSION_AMOUNT_12";
        fieldNames[216] = "REVENUE_CONCESSION_SIGN_12";
        fieldNames[217] = "REVENUE_CONCESSION_AMOUNT_12";
        fieldNames[218] = "REVENUE_LOAD_SIGN_12";
        fieldNames[219] = "REVENUE_LOAD_AMOUNT_12";
        fieldNames[220] = "ORDER_REFERENCE_NUMBER_12";
        fieldNames[221] = "FILLER_12_j";
        fieldNames[222] = "INPUT_COMMISSION_SIGN_12";
        fieldNames[223] = "INPUT_COMMISSION_AMOUNT_12";
        fieldNames[224] = "CONFIRM_LEGEND_CODE_12_a";
        fieldNames[225] = "CONFIRM_LEGEND_CODE_12_b";
        fieldNames[226] = "FILLER_12_k";
        fieldNames[227] = "ORIGINAL_DESCRIPTION_12_a";
        fieldNames[228] = "RECORD_NUMBER_13";
        fieldNames[229] = "ORIGINAL_DESCRIPTION_13_b";
        fieldNames[230] = "EXECUTION_TIME_13";
        fieldNames[231] = "REGISTERED_REP_PAY_TO_REP_13";
        fieldNames[232] = "CLEARING_CHARGE_SIGN_13";
        fieldNames[233] = "CLEARING_CHARGE_13";
        fieldNames[234] = "EXECUTION_FEE_SIGN_13";
        fieldNames[235] = "EXECUTION_FEE_13";
        fieldNames[236] = "FOREIGN_SURCHARGE_SIGN_13";
        fieldNames[237] = "FOREIGN_SURCHARGE_13";
        fieldNames[238] = "FILLER_13_l";
        fieldNames[239] = "SUPER_BRANCH_13";
        fieldNames[240] = "FILLER_13_m";


    }

    public String[] getFieldNames() {
        return this.fieldNames;
    }

    public void setFieldValues(String[] lineWOBreak, int numLineWOBreak) {
        fieldValues = new String[numLineWOBreak][numOfFields];
        for (int a = 0; a < numLineWOBreak; ++a) {
            for (int b = 0; b < numOfFields; ++b) {
                if (b != 0) {
                    fieldValues[a][b] = lineWOBreak[a].substring(calcFieldLengths[b] - origFieldLengths[b] - 1, calcFieldLengths[b] - 1);
                } else {
                    fieldValues[a][b] = lineWOBreak[a].substring(origFieldLengths[b] - origFieldLengths[b], origFieldLengths[b]);
                }
                System.out.print(fieldValues[a][b] + " | ");
            }
        }
    }

    public String[][] getFieldValues() {
        return this.fieldValues;
    }

    public void setFixToCSV(String[] str, int numLineWOBreak) {
        lineCSV = new String[numLineWOBreak];
        for (int a = 0; a < numLineWOBreak; ++a) {
            StringBuffer tempString = new StringBuffer(str[a]);
            for (int b = 0; b < this.numOfFields - 1; ++b) {
                tempString = tempString.insert(calcFieldLengths[b], delim);
            }
            lineCSV[a] = new String(tempString.toString());
        }
    }

    public String[] getFixToCSV() {
        return this.lineCSV;
    }

}
