import { DatePipe } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'clapp-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.scss']
})
export class AppointmentComponent implements OnInit {
  @Input() dataSource: any[];
  @Input() appointmentDate;
  @Output() loadAppointmentAction : EventEmitter<any> = new EventEmitter();
  @Input() loading;
  
  constructor(
    private datePipe: DatePipe
  ) { }

  ngOnInit() {
  }

  changeDate(isBefore){
    let dateString = this.appointmentDate+'T00:00:00' 
    let newDate = new Date(dateString);

    if(isBefore)
    newDate.setDate( newDate.getDate() - 1 );
    else
    newDate.setDate( newDate.getDate() + 1 );

    this.appointmentDate = this.datePipe.transform(newDate, 'yyyy-MM-dd');

    this.loadAppointmentAction.emit(this.appointmentDate);
  }

}
