import { environment } from "./../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { DatePipe } from "@angular/common";

@Component({
  selector: "clapp-ro-list",
  templateUrl: "./ro-list.component.html",
  styleUrls: ["./ro-list.component.scss"],
})
export class RoListComponent implements OnInit {
  dataSource: any[];
  stores: any[];
  dealerId:any;
  loadingAppointment: boolean;
  closedDataSource: any[];
  loadingClosed: boolean;
  advisorId: string;
  openRos: any[];
  loadingOpenRos: boolean;
  advisors: any[];
  appointmentDate: any;
  closedDate: any;
  loadingSales: boolean;
  sales: any[];
  soldDate: string;
  constructor(private _httpClient: HttpClient,
    public _router: Router,
    private datePipe: DatePipe) {}

  ngOnInit() {
    this.dealerId = '3PAAIMACHINESLIVE2';
    this.advisorId = 'ALL';

    this.appointmentDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
    const newDate = new Date();
    newDate.setDate( newDate.getDate() - 1 );
    this.closedDate = this.datePipe.transform(newDate, 'yyyy-MM-dd');

    this.soldDate = this.datePipe.transform(newDate, 'yyyy-MM-dd');

    this.loadStores();
    
    this.loadAppointmentList();
    this.loadClosedList();
    this.loadOpenRos();
    this.loadSales();
  }
  loadClosedRoOnDateChange(event){
    this.closedDate = event;
    this.loadClosedList();
  }

  loadSalesDateRange(event){
    this.soldDate = event;
    this.loadSales();
  }
  loadAppointmentOnDateChange(event){
    this.appointmentDate = event;
    this.loadAppointmentList();
  }
  onChange(delaerId){
    console.log(delaerId);
    this.dealerId = delaerId;
    this.advisorId = 'ALL';

    this.loadAppointmentList();
    this.loadClosedList();
    this.loadOpenRos();
    this.loadAdvisors();
    this.loadSales();
    
  }

  loadAdvisors() {
    this.advisors= [];
    var selectedStore =  this.stores.filter(x => x.storeId == this.dealerId);
    this._httpClient
      .get(environment.apiUrl + "/api/getAdivsorByStore/"+selectedStore[0].id)
      .subscribe((data: any) => {
        if (data && data.length > 0) this.advisors = data;
        this.advisors.unshift({
          empId:'ALL',
          firstName:'ALL',
        });
      });
  }

  loadAppointmentList() {
    this.dataSource = [];
    this.loadingAppointment = true;
    this._httpClient
      .get(environment.apiUrl + "/api/getAppointmentByStoreIdAndDate/"+this.dealerId+'/'+this.appointmentDate)
      .subscribe((data: any) => {
        if (data && data.length > 0) this.dataSource = data;
        this.loadingAppointment = false;
      });
  }

  loadClosedList() {
    this.closedDataSource = [];
    this.loadingClosed = true;
    this._httpClient
      .get(environment.apiUrl + "/api/getClosedRoByStoreIdAndDateAndAdvisorNo/"+this.dealerId+'/'+this.closedDate+'/'+this.advisorId)
      .subscribe((data: any) => {
        if (data && data.length > 0) this.closedDataSource = data;
        this.loadingClosed = false;
      });
  }

  loadSales() {
    this.sales= [];
    this.loadingSales = true;


    this._httpClient
      .get(environment.apiUrl + "/api/getSalesByDealerAndDate/"+this.dealerId+'/'+this.soldDate)
      .subscribe((data: any) => {
        if (data && data.length > 0) this.sales = data;
        this.loadingSales = false;
      });
  }
  
  loadOpenRos() {
    this.openRos= [];
    this.loadingOpenRos = true;
    const obj = {
      statusCode :['I91','I93','I98','H98','C97','C94'],
      storeId:this.dealerId,
      advisorNo : this.advisorId
    }
    this._httpClient
      .post(environment.apiUrl + "/api/getOpenRoBtStoreId",obj)
      .subscribe((data: any) => {
        if (data && data.length > 0) this.openRos = data;
        this.loadingOpenRos = false;
      });
  }

  gotoDetails(roNo){
    this._router.navigate(['rodetails/'+roNo]);
  }

  onAdvisorChange(advisorId){
    this.advisorId = advisorId;
    this.loadAppointmentList();
    this.loadClosedList();
    this.loadOpenRos();
  }

  loadStores() {
    this.stores= [];

    this._httpClient
      .get(environment.apiUrl + "/api/getAllStores")
      .subscribe((data: any) => {
        if (data && data.length > 0) {
            data.forEach(x =>{
            this.stores.push({
              storeId:x.storeId,
              value : x.storeName,
              id:x.id,
            });
          });
        }
        this.loadAdvisors();
      });
  }
}
