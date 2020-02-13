package com.jiaoxf.pojo;

public class AirLine {
private int id;
private String airno;
private int time;
private int price;
private int takeoffid;
private int landid;
private AirPort takeoffPort;
private AirPort landPort;

public AirPort getTakeoffPort() {
	return takeoffPort;
}
public void setTakeoffPort(AirPort takeoffPort) {
	this.takeoffPort = takeoffPort;
}
public AirPort getLandPort() {
	return landPort;
}
public void setLandPort(AirPort landPort) {
	this.landPort = landPort;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getAirno() {
	return airno;
}
public void setAirno(String airno) {
	this.airno = airno;
}
public int getTime() {
	return time;
}
public void setTime(int time) {
	this.time = time;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getTakeoffid() {
	return takeoffid;
}
public void setTakeoffid(int takeoffid) {
	this.takeoffid = takeoffid;
}
public int getLandid() {
	return landid;
}
public void setLandid(int landid) {
	this.landid = landid;
}

}
