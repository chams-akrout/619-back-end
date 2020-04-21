package com.barcode.BarcodeProject.web.ProductDto;

public class ForeignBarCodeDto {
	private String barcode;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ForeignBarCodeDto [barcode=");
		builder.append(barcode);
		builder.append("]");
		return builder.toString();
	}

}
