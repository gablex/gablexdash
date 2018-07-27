// private String[][] getAllData() {
//                          
//        int test = 0;
//        String[][] ret = null;
//        Sheet sheet = null;
//        try {
//            sheet = workbook.getSheet(sheetname);
//        } catch (Exception o) {
//            System.out.println("sheet is null");
//
//        }
// try {
//  ret = new String[rows][cols];
//for (int d = 0; d < rows; d++) {
//                Row row = sheet.getRow(d);
////                String[] kevol = new String[cols];
//                for (int j = 0; j < cols; j++) {             
//                    
//                    if (rows == 1 && cols == 1) {
//                          ret[d][j] = " ";
//                        break;
//                    }
//                    try {
//                        Cell cell2 = row.getCell(j);
//                        //test code
////                        cell2.setCellType(1);
//                        switch (cell2.getCellType()) {
//                            case Cell.CELL_TYPE_STRING:
//                                if (cell2.equals("")) {
//
//                                     ret[d][j] = " ";
//                                } else {
//                                     System.out.println("hhhhhhhhhhhhhhhhhhhhhhhh String ;;;;");
//                                    ret[d][j]  = cell2.getRichStringCellValue().getString();
//                                }
//
//                                break;
//                            case Cell.CELL_TYPE_NUMERIC:
//                                if (HSSFDateUtil.isCellDateFormatted(cell2)) {
//                                System.out.println("hhhhhhhhhhhhhhhhhhhhhhhh date3 ;;;;");
//                                    ret[d][j]  = String.valueOf(cell2.getDateCellValue());
//                                } else {
//                                     ret[d][j] = String.valueOf(cell2.getNumericCellValue());
//                                }
//                                break;
//                            case Cell.CELL_TYPE_BOOLEAN:
//                                  ret[d][j] = String.valueOf(cell2.getBooleanCellValue());
//                                break;
//                            case Cell.CELL_TYPE_FORMULA:
//                                 ret[d][j] = (cell2.getCellFormula());
//                                break;
//                            case Cell.CELL_TYPE_BLANK:
//                               ret[d][j]  = "";
//                            default:
//                                         System.out.println();
//                        }
//
//                    } catch (Exception ex) {
//                        ret[d][j] = "";
//
//                    }
// 
//                }
//              
//// tableData.add(Arrays.asList(kevol));
//            }
//
//            
//
// } catch (Exception e) {
//            messager = new FacesMessage(" Not Succesful", " Check the columns/ FORMAT of your Excel file.");
//            FacesContext.getCurrentInstance().addMessage(null, messager);
//        }
// return ret;
//    }