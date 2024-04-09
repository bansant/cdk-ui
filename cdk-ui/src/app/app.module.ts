import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { NgModule, APP_INITIALIZER, ErrorHandler } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import 'hammerjs';
// carbon-components-angular default imports
import { UIShellModule, PlaceholderModule, SelectModule, ModalModule, InputModule, ButtonModule, NotificationModule, TilesModule, DropdownModule, DropdownList, ComboBoxModule, AccordionModule } from 'carbon-components-angular';
import { Notification20Module } from '@carbon/icons-angular/lib/notification/20';
import { UserAvatar20Module } from '@carbon/icons-angular/lib/user--avatar/20';
import { AppSwitcher20Module } from '@carbon/icons-angular/lib/app-switcher/20';
import { Help20Module } from '@carbon/icons-angular/lib/help/20';
import { ChevronDownGlyphModule, ChevronLeftGlyphModule, EmailModule } from '@carbon/icons-angular';
import { ChevronRightGlyphModule } from '@carbon/icons-angular';

import { TranslateModule } from '@ngx-translate/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Collaborate32Module } from '@carbon/icons-angular/lib/collaborate/32';

import { CookieService } from 'ngx-cookie-service';

import { MatCard, MatCardModule, MatDialogModule, MatNativeDateModule, MatProgressSpinnerModule, MatTabsModule } from '@angular/material';
import { TourMatMenuModule } from 'ngx-tour-md-menu';
import { RouterModule } from '@angular/router';
import { NgIdleModule } from '@ng-idle/core';

import { OverlayModule } from '@angular/cdk/overlay';
import { RoListComponent } from './ro-list/ro-list.component';
import { RodetailsComponent } from './rodetails/rodetails.component';
import { DatePipe } from '@angular/common';

import { ClosedRoComponent } from './ro-list/closed-ro/closed-ro.component';
import { OpenRoComponent } from './ro-list/open-ro/open-ro.component';
import { OpenRoDetailsComponent } from './open-ro-details/open-ro-details.component';
import { SwimLaneComponent } from './ro-list/swim-lane/swim-lane.component';
import { AppointmentComponent } from './ro-list/appointment/appointment.component';
import { AuditTrailComponent } from './audit-trail/audit-trail.component';
import { SalesComponent } from './ro-list/sales/sales.component';
import { RoDetailPopoverComponent } from './open-ro-details/ro-detail-popover/ro-detail-popover.component';
@NgModule({
	declarations: [	
		AppComponent,
      RoListComponent,
      RodetailsComponent,
      ClosedRoComponent,
      OpenRoComponent,
      OpenRoDetailsComponent,
      SwimLaneComponent,
      AppointmentComponent,
      AuditTrailComponent,
      SalesComponent,
      RoDetailPopoverComponent
   ],
	imports: [
		BrowserModule,
		BrowserAnimationsModule,
		FormsModule,
		AppRoutingModule,
		OverlayModule,
		HttpClientModule,
		UIShellModule,
		PlaceholderModule,
		Notification20Module,
		UserAvatar20Module,
		AppSwitcher20Module,
		Help20Module,
		TranslateModule.forRoot(),
		SelectModule,
		ModalModule,
		InputModule,
		ButtonModule,
		MatProgressSpinnerModule,
		NotificationModule,
		TourMatMenuModule.forRoot(),
		RouterModule,
		TilesModule,
		ChevronLeftGlyphModule,
		ChevronRightGlyphModule,
		NgIdleModule.forRoot(),
		ChevronDownGlyphModule,
		EmailModule,
		Collaborate32Module,
		DropdownModule,
		ComboBoxModule,
		MatNativeDateModule,
		MatCardModule,
		MatTabsModule,
		MatDialogModule
	],
	providers: [
		DatePipe,
	],
	exports: [
		DropdownList,
		MatCard
	],
	entryComponents:[AuditTrailComponent,RoDetailPopoverComponent],
	bootstrap: [AppComponent]
})
export class AppModule { }
