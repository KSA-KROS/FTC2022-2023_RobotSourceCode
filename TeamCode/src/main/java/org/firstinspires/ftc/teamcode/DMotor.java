package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DMotor
{
        private DcMotor motor;
        private int dir; //1 or -1
        private double tpr;
        private boolean finish;
        private double i_position;
        private double angle;
        private Telemetry telemetry;

        public void init(HardwareMap hardwaremap, Telemetry telemetry, String name, int dir)
        {
                motor = hardwaremap.get(DcMotor.class, name);
                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                tpr = motor.getMotorType().getTicksPerRev();
                finish = true;
                this.dir = dir;
                this.telemetry = telemetry;
                this.finish = false;
        }
        public void update()
        {
                if(!finish)
                {
                        if((angle>0)&&(get_position()-(i_position+angle) > 0))
                        {
                                motor.setPower(0.0);
                                finish = true;
                        }
                        else if((angle<0)&&(get_position()-(i_position+angle) < 0))
                        {
                                motor.setPower(0.0);
                                finish =  true;
                        }
                }
        }

        public double get_position()
        {
                return motor.getCurrentPosition()/tpr;
        }
        public void move(double speed, double angle)
        {
                finish = false;
                i_position = get_position();
                this.angle = angle*dir;
                if(this.angle > 0)
                {
                        motor.setPower(speed);
                }
                else
                {
                        motor.setPower(-speed);
                }
        }

        public void move(double speed)
        {
                motor.setPower(speed*dir);
        }

        public boolean finish()
        {
                return finish;
        }
}