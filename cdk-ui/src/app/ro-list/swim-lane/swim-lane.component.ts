import { RoDetailPopoverComponent } from './../../open-ro-details/ro-detail-popover/ro-detail-popover.component';
import { AuditTrailComponent } from './../../audit-trail/audit-trail.component';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../../environments/environment';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { MatDialog, MatDialogRef } from '@angular/material';

@Component({
  selector: 'clapp-swim-lane',
  templateUrl: './swim-lane.component.html',
  styleUrls: ['./swim-lane.component.scss']
})
export class SwimLaneComponent implements OnInit,AfterViewInit {
  matDialogRef: MatDialogRef<AuditTrailComponent>;
  matDialogRefRoDetails: MatDialogRef<RoDetailPopoverComponent>;
  dataSource: any[];
  appointmentDate: string;
  closedDate: string;
  dealerId: string;
  checkedInRos: any[];
  loadingAppointment: boolean;
  loadingCheckedIn: boolean;
  closedDataSource: any[];
  loadingClosed: boolean;
  preAssignedRos: any[];
  loadingPreAssigned: boolean;
  workingRos: any[];
  loadingWorking: boolean;
  loadingVehicleDisabled: boolean;
  partsHold: any[];
  loadingPreInvoiced: boolean;
  preInvoicedData: any;
  readyToPostData: any[];
  loadingReadyToPost: boolean;
  stores: any[];
  advisors: any[];
  advisorId: any;
  sales: any[];
  loadingSales: boolean;
  soldDate: string;
  authHold: any[];
  misc: any[];

  constructor(private _httpClient : HttpClient,
    private datePipe: DatePipe,private modalService: MatDialog) { }

  ngOnInit() {
    this.appointmentDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
    const newDate = new Date();
    newDate.setDate( newDate.getDate() - 1 );
    this.closedDate = this.datePipe.transform(newDate, 'yyyy-MM-dd');

    this.soldDate = this.datePipe.transform(newDate, 'yyyy-MM-dd');
  }

  ngAfterViewInit() {
    this.dealerId = '3PAAIMACHINESLIVE2';
    this.advisorId = 'ALL';

    this.loadAppointmentList()
    this.loadCheckedInRos();
    this.loadClosedList()
    this.loadPreassignedRos();
    this.loadWorkingRos();
    this.loadVehicleDisabled();
    this.loadPreInvoiced();
    this.loadReadyToPost();
    this.loadStores();
    this.loadSales();
    
  }

  refresh(){
    this.loadAppointmentList()
    this.loadCheckedInRos();
    this.loadClosedList()
    this.loadPreassignedRos();
    this.loadWorkingRos();
    this.loadVehicleDisabled();
    this.loadPreInvoiced();
    this.loadReadyToPost();
    this.loadStores();
    this.loadSales();
  }
  changeClosedDate(isBefore){
    let dateString = this.closedDate+'T00:00:00' 
    let newDate = new Date(dateString);

    if(isBefore)
    newDate.setDate( newDate.getDate() - 1 );
    else
    newDate.setDate( newDate.getDate() + 1 );

    this.closedDate = this.datePipe.transform(newDate, 'yyyy-MM-dd');

    this.loadClosedList()
    //this.loadCheckedInRos();
  }

  changeDate(isBefore){
    let dateString = this.appointmentDate+'T00:00:00' 
    let newDate = new Date(dateString);

    if(isBefore)
    newDate.setDate( newDate.getDate() - 1 );
    else
    newDate.setDate( newDate.getDate() + 1 );

    this.appointmentDate = this.datePipe.transform(newDate, 'yyyy-MM-dd');

    this.loadAppointmentList()
    //this.loadCheckedInRos();
  }

  changeSaleDate(isBefore){
    let dateString = this.soldDate+'T00:00:00' 
    let newDate = new Date(dateString);

    if(isBefore)
    newDate.setDate( newDate.getDate() - 1 );
    else
    newDate.setDate( newDate.getDate() + 1 );

    this.soldDate = this.datePipe.transform(newDate, 'yyyy-MM-dd');

    this.loadSales();

  }
  onChange(delaerId){
    console.log(delaerId);
    this.dealerId = delaerId;
    this.advisorId = 'ALL';
    this.loadAppointmentList();
    this.loadCheckedInRos();
    this.loadClosedList();
    this.loadPreassignedRos();
    this.loadWorkingRos();
    this.loadVehicleDisabled();
    this.loadPreInvoiced();
    this.loadReadyToPost();
    this.loadAdvisors();
    this.loadSales();
  }

  onAdvisorChange(advisorId){
    
    this.advisorId = advisorId;
    this.loadAppointmentList();
    this.loadCheckedInRos();
    this.loadClosedList();
    this.loadPreassignedRos();
    this.loadWorkingRos();
    this.loadVehicleDisabled();
    this.loadPreInvoiced();
    this.loadReadyToPost();
  }

  loadAppointmentList() {
    this.dataSource = [];
    this.loadingAppointment = true;
    this._httpClient
      .get(environment.apiUrl + "/api/getAppointmentByStoreIdAndDate/"+this.dealerId+'/'+this.appointmentDate)
      .subscribe((data: any) => {
        if (data && data.length > 0) {
          data.sort((a,b) => a.appointment.appointmentTime.localeCompare(b.appointment.appointmentTime));
          this.dataSource = data;
        }

        
        this.loadingAppointment = false;
      });
  }
  loadClosedList() {
    this.closedDataSource = [];
    this.loadingClosed = true;
    this._httpClient
      .get(environment.apiUrl + "/api/getClosedRoByStoreIdAndDateAndAdvisorNo/"+this.dealerId+'/'+this.closedDate+'/'+this.advisorId)
      .subscribe((data: any) => {
        if (data && data.length > 0) {
          this.closedDataSource = data;
        }
        this.loadingClosed = false;
      });
  }

  loadCheckedInRos() {
    this.checkedInRos= [];
    this.loadingCheckedIn = true;
    const obj = {
      statusCode :['I91'],
      storeId:this.dealerId,
      advisorNo : this.advisorId
    }
    this._httpClient
      .post(environment.apiUrl + "/api/getOpenRoBtStoreId",obj)
      .subscribe((data: any) => {
        if (data && data.length > 0) {
          data.sort((a,b) => a.daysDiff - b.daysDiff);
          this.checkedInRos = data;
        }
        this.loadingCheckedIn = false;
      });
  }

  loadPreassignedRos() {
    this.preAssignedRos= [];
    this.loadingPreAssigned = true;
    const obj = {
      statusCode :['I93'],
      storeId:this.dealerId,
      advisorNo : this.advisorId
    }
    this._httpClient
      .post(environment.apiUrl + "/api/getOpenRoBtStoreId",obj)
      .subscribe((data: any) => {
        if (data && data.length > 0) 
        {
          data.sort((a,b) => a.daysDiff - b.daysDiff);
          this.preAssignedRos = data;
        }
        this.loadingPreAssigned = false;
      });
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


  loadWorkingRos() {
    this.workingRos= [];
    this.loadingWorking = true;
    const obj = {
      statusCode :['I98'],
      storeId:this.dealerId,
      advisorNo : this.advisorId
    }
    this._httpClient
      .post(environment.apiUrl + "/api/getOpenRoBtStoreId",obj)
      .subscribe((data: any) => {
        if (data && data.length > 0) {
          data.sort((a,b) => a.daysDiff - b.daysDiff);
          this.workingRos = data;
        }
        this.loadingWorking = false;
      });
  }

  loadVehicleDisabled() {
    this.partsHold= [];
    this.authHold = [];
    this.misc = [];
    this.loadingVehicleDisabled = true;
    const obj = {
      statusCode :['H98'],
      storeId:this.dealerId,
      advisorNo : this.advisorId
    };
    console.log('loadVehicleDisabled');

    this._httpClient
      .post(environment.apiUrl + "/api/getOpenRoBtStoreId",obj)
      .subscribe((data: any) => {
        if (data && data.length > 0) {
          data.sort((a,b) => a.daysDiff - b.daysDiff);
          this.partsHold = data.filter(x=> x.lineItemList && x.lineItemList.length>0 && x.lineItemList.some(x=> x.statusCode =='H10'));
          this.authHold = data.filter(x=> x.lineItemList && x.lineItemList.length>0 && x.lineItemList.some(x=> x.statusCode =='H30'));
          this.misc  = data.filter(x=> x.lineItemList && x.lineItemList.length>0 && x.lineItemList.some(x=> x.statusCode =='H20' 
          || x.statusCode =='H25' || x.statusCode =='H25' || x.statusCode =='H40' || x.statusCode =='H90' || x.statusCode =='H97'));
        }
        this.loadingVehicleDisabled = false;
      });
  }

  loadPreInvoiced() {
    this.preInvoicedData= [];
    this.loadingPreInvoiced = true;
    const obj = {
      statusCode :['C97'],
      storeId:this.dealerId,
      advisorNo : this.advisorId
    };

    this._httpClient
      .post(environment.apiUrl + "/api/getOpenRoBtStoreId",obj)
      .subscribe((data: any) => {
        if (data && data.length > 0) {
          data.sort((a,b) => a.daysDiff - b.daysDiff);
          this.preInvoicedData = data;
        }
        this.loadingPreInvoiced = false;
      });
  }

  loadReadyToPost() {
    this.readyToPostData= [];
    this.loadingReadyToPost = true;
    const obj = {
      statusCode :['C94'],
      storeId:this.dealerId,
      advisorNo : this.advisorId
    };

    this._httpClient
      .post(environment.apiUrl + "/api/getOpenRoBtStoreId",obj)
      .subscribe((data: any) => {
        if (data && data.length > 0) this.readyToPostData = data;
        this.loadingReadyToPost = false;
      });
  }

  loadSales() {
    this.sales= [];
    this.loadingSales = true;
    const obj = {
      statusCode :['C94'],
      storeId:this.dealerId,
      advisorNo : this.advisorId
    };

    this._httpClient
      .get(environment.apiUrl + "/api/getSalesByDealerAndDate/"+this.dealerId+'/'+this.soldDate)
      .subscribe((data: any) => {
        if (data && data.length > 0) this.sales = data;
        this.loadingSales = false;
      });
  }

  openStatusTrail(item){
    console.log(item);
    this.matDialogRef = this.modalService.open(AuditTrailComponent, {
      data: { rowNum: item.service.roNumber },
      disableClose: true
    });

    this.matDialogRef.afterClosed().subscribe(res => {
      if ((res == true)) {
      
      }
    });
  }

  openRoDetails(item,isClosed=false){
    console.log(item);
    this.matDialogRefRoDetails = this.modalService.open(RoDetailPopoverComponent, {
      data: { rowNum: item.service.roNumber,closed:isClosed },
      disableClose: true
    });

    this.matDialogRefRoDetails.afterClosed().subscribe(res => {
      if ((res == true)) {
      
      }
    });
  }

  getDistinctTechnicians(array){

    var flags = [], output = [], l = array.length, i;
    for( i=0; i<l; i++) {
        if( flags[array[i].empId]) continue;
        flags[array[i].empId] = true;
        output.push(array[i]);
    }
    return output;
  }
}
