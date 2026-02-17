import {GenderI18nComponent} from "@app/author/gender-i18n/gender-i18n.component";
import {GenderEnum} from "@app/wto/gender.enum";
import {ComponentFixture, TestBed} from "@angular/core/testing";

describe('GenderI18n', () => {
    let component: GenderI18nComponent;
    let fixture: ComponentFixture<GenderI18nComponent>;

    beforeEach(() => {
        fixture = TestBed.createComponent(GenderI18nComponent);
        component = fixture.componentInstance;
    });

    it('should display correct text for MALE', () => {
        component.enumValue = GenderEnum.MALE;

        fixture.detectChanges();

        expect(fixture.nativeElement.textContent).toBe('MALE');
    });

    it('should display correct text for FEMALE', () => {
        component.enumValue = GenderEnum.FEMALE;

        fixture.detectChanges();

        expect(fixture.nativeElement.textContent).toBe('FEMALE');
    });

    // checking if everything is translated
    Object.values(GenderEnum).forEach((value) => {
        it(`should translate ${value}`, () => {
            component.enumValue = value;

            fixture.detectChanges();

            expect(fixture.nativeElement.textContent).not.toBe('');
        });
    });
});
